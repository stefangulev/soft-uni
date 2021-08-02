package softuni.exam.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "planes")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlaneSeedDto {
    @XmlElement(name = "plane")
   private List<PlaneSeedSingleDto> planes;

    public List<PlaneSeedSingleDto> getPlanes() {
        return planes;
    }

    public void setPlanes(List<PlaneSeedSingleDto> planes) {
        this.planes = planes;
    }
}
