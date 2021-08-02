package softuni.exam.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class XmlParserImpl implements XmlParser {

    @Override
    public <T> T fromFile(String pathName, Class<?> toClass) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(toClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T)unmarshaller.unmarshal(new FileReader(pathName));
    }
}
