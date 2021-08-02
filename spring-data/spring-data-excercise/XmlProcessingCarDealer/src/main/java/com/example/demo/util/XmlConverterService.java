package com.example.demo.util;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface XmlConverterService {
    <T>void toFile(String pathName, T entity) throws JAXBException;
    <T> T fromFile(String pathName, Class<T> toClass) throws JAXBException, FileNotFoundException;
}
