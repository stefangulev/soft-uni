package hiberspring.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeSeedDto {
    @XmlElement(name = "employee")
   private List<EmployeeSeedSingleDto> employees;

    public List<EmployeeSeedSingleDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeSeedSingleDto> employees) {
        this.employees = employees;
    }
}
