package cl.faceledev.xpc.service.impl;

import cl.faceledev.xpc.service.ArchivoService;
import io.facele.facilito.dto.v10.xpc.InputUploadCrear;
import io.facele.facilito.dto.v10.xpc.OutputUploadCrear;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class ArchivoServiceImpl implements ArchivoService {
    @Value("${file.directory.path}")
    private String filePath;

    @Override
    public OutputUploadCrear uploadCrear(InputUploadCrear crear) {
        OutputUploadCrear outputUploadCrear = new OutputUploadCrear();
        String entryName = "InputExcepcionCrear";

        try (FileOutputStream stream = new FileOutputStream(filePath+"\\"+entryName+".zip")) {
            stream.write(crear.getZipBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        outputUploadCrear.setNombreFile(entryName + ".zip");

        return outputUploadCrear;
    }
}
