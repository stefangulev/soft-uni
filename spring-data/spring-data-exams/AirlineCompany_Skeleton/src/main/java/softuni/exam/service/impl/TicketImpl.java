package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.TicketSeedDto;
import softuni.exam.models.entities.Ticket;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.repository.TicketRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TicketService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketImpl implements TicketService {
    private final String XML_FILE_PATH = "src/main/resources/files/xml/tickets.xml";
    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final TownRepository townRepository;
    private final PassengerRepository passengerRepository;
    private final PlaneRepository planeRepository;

    public TicketImpl(TicketRepository ticketRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil, TownRepository townRepository, PassengerRepository passengerRepository, PlaneRepository planeRepository) {
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.townRepository = townRepository;
        this.passengerRepository = passengerRepository;
        this.planeRepository = planeRepository;
    }


    @Override
    public boolean areImported() {
        return ticketRepository.count() >0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        return Files.readString(Path.of(XML_FILE_PATH));
    }

    @Override
    public String importTickets() throws JAXBException, FileNotFoundException {
      StringBuilder sb = new StringBuilder();
        TicketSeedDto ticketSeedDto = xmlParser.fromFile(XML_FILE_PATH, TicketSeedDto.class);
        ticketSeedDto.getTickets().stream().filter(d -> {
            boolean isValid = validationUtil.isValid(d);
            sb.append(isValid ? String.format("Successfully imported Ticket %s - %s", d.getFromTown().getName(), d.getToTown().getName()) :
                    "Invalid Ticket").append(System.lineSeparator());
            return isValid;
        }).map(d -> {
            Ticket ticket = modelMapper.map(d, Ticket.class);
            ticket.setFromTown(townRepository.findTownByName(d.getFromTown().getName()));
            ticket.setToTown(townRepository.findTownByName(d.getToTown().getName()));
            ticket.setPassenger(passengerRepository.findPassengerByEmail(d.getPassenger().getEmail()));
            ticket.setPlane(planeRepository.findPlaneByRegisterNumber(d.getPlane().getRegisterNumber()));
            return ticket;
        }).forEach(ticketRepository::save);
        return sb.toString();
    }
}
