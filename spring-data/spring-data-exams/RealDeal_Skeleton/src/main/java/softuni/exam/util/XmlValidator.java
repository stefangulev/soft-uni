package softuni.exam.util;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface XmlValidator {

    <T> T fromFile(String pathName, Class<T> toClass) throws JAXBException, FileNotFoundException;
}
