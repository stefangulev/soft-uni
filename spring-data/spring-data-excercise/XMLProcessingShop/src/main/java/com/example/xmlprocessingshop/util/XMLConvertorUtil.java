package com.example.xmlprocessingshop.util;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface XMLConvertorUtil {

    <T> T fromFile(String filepath, Class<T> toClass) throws JAXBException, FileNotFoundException;
    <T> void toFile(String filePath, T entity) throws JAXBException, IOException;
}
