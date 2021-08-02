package com.example.demo.util;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

@Component
public class XmlConverter implements XmlConverterService{

    public XmlConverter() {

    }

    @Override
    public <T> void toFile(String pathName, T entity) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(entity.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(entity, new File(pathName));
    }

    @Override
    public <T> T fromFile(String pathName, Class<T> toClass) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(toClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T)unmarshaller.unmarshal(new FileReader(pathName));
    }
}
