package com.example.football.util;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface XmlParserUtil {
    <T> T fromFile(String pathName, Class<?> toClass) throws JAXBException, FileNotFoundException;
}
