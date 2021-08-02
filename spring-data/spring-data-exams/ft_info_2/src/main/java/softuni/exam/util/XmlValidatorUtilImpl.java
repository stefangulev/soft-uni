package softuni.exam.util;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Component
public class XmlValidatorUtilImpl implements XmlValidatorUtil{

    @Override
    public <T> T fromFile(String filePath, Class<T> toClass) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(toClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new FileReader(filePath));
    }
}
