package softuni.exam.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tickets")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketSeedDto {
    @XmlElement(name = "ticket")
    private List<TicketSeedSingleDto> tickets;

    public List<TicketSeedSingleDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketSeedSingleDto> tickets) {
        this.tickets = tickets;
    }
}
