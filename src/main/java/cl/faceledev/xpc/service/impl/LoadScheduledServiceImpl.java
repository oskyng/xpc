package cl.faceledev.xpc.service.impl;

import cl.faceledev.xpc.service.ExcepcionService;
import cl.faceledev.xpc.service.LoadScheduledService;
import io.facele.facilito.dto.v10.xpc.InputExcepcionCrear;
import jakarta.xml.bind.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class LoadScheduledServiceImpl implements LoadScheduledService {
    private final Logger log = Logger.getLogger(this.getClass().getName());

    @Autowired
    private ExcepcionService excepcionService;
    @Value("${file.directory.path}")
    private String filePath;
    @Value("${file.directory.move}")
    private String fileMove;
    @Value("${file.name}")
    private String fileName;
    @Value("${file.extention}")
    private String fileExtention;

    @Override
    public void procesaExcepcionesTask() {
        log.info("init process load exception");
        try {
            File dir = new File(filePath);
            File[] files = dir.listFiles((dir1, name) -> name.startsWith(fileName) && name.endsWith(fileExtention));
            if (files != null && files.length > 0) {
                log.info("Files to process: "+files.length);
                for(File file:files) {
                    byte[] buffer = new byte[1024];
                    ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
                    ZipEntry zipEntry = zis.getNextEntry();

                    while (zipEntry != null) {
                        File newFile = this.newFile(dir, zipEntry);
                        if (zipEntry.isDirectory()) {
                            if (!newFile.isDirectory() && !newFile.mkdirs()) {
                                throw new IOException("Failed to create directory " + newFile);
                            }
                        } else {
                            File parent = newFile.getParentFile();
                            if (!parent.isDirectory() && !parent.mkdirs()) {
                                throw new IOException("Failed to create directory " + parent);
                            }

                            // write file content
                            FileOutputStream fos = new FileOutputStream(newFile);
                            int len;
                            while ((len = zis.read(buffer)) > 0) {
                                fos.write(buffer, 0, len);
                            }
                            fos.close();

                            JAXBContext jc = JAXBContext.newInstance(InputExcepcionCrear.class);
                            Unmarshaller unmarshaller = jc.createUnmarshaller();
                            InputExcepcionCrear inputExcepcionCrear = (InputExcepcionCrear) unmarshaller.unmarshal(newFile);

                            excepcionService.crearExcepcion(inputExcepcionCrear);

                        }
                        zipEntry = zis.getNextEntry();
                    }
                    zis.closeEntry();
                    zis.close();

                    Path fileToMovePath = Paths.get(filePath+file.getName());
                    Path targetPath = Paths.get(fileMove+file.getName());
                    Files.move(fileToMovePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                }
            } else {
                log.info("No files found");
            }

            log.info("finalized process...");
        } catch (IOException | JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }
}
