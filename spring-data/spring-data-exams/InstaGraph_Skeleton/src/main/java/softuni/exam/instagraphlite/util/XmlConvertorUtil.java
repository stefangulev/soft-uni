package softuni.exam.instagraphlite.util;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface XmlConvertorUtil {

    <T> T fromFile(String filePath, Class<T> toClass) throws JAXBException, FileNotFoundException;
}
