package softuni.exam.util;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface XmlValidatorUtil {
    <T> T fromFile(String filePath, Class<T> toClass) throws JAXBException, IOException;
}
