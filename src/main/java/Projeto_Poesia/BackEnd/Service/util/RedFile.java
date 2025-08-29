package Projeto_Poesia.BackEnd.Service.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

public class RedFile {
    public static String redFile(String classpathlocation){
        try(InputStream is = RedFile.class.getClassLoader().getResourceAsStream(classpathlocation)){
            if(is == null){
                throw new IllegalArgumentException("Resource not found: " + classpathlocation);
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
 
