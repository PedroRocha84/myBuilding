package pt.pedrorocha.mybuilding.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.pedrorocha.mybuilding.dto.TicketDto;
import pt.pedrorocha.mybuilding.dto.TicketResponseDto;
import pt.pedrorocha.mybuilding.mapper.TicketMapper;
import pt.pedrorocha.mybuilding.model.Building;
import pt.pedrorocha.mybuilding.model.Resident;
import pt.pedrorocha.mybuilding.model.Ticket;
import pt.pedrorocha.mybuilding.repository.BuildingRepository;
import pt.pedrorocha.mybuilding.repository.ResidentRepository;
import pt.pedrorocha.mybuilding.repository.TicketRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private TicketMapper ticketMapper;

    public List<Ticket> list(){
        return new ArrayList<>(ticketRepository.findAll());
    }

    public TicketResponseDto add(TicketDto ticketdto){
        if(ticketdto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket can't be null");
        }

        Resident resident = residentRepository.findById(ticketdto.getResidentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Resident not found"));

        Building building = buildingRepository.findById(ticketdto.getBuildingId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Building not found"));

        Ticket ticket = new Ticket();
        ticket.setDescription(ticketdto.getDescription());
        ticket.setTitle(ticketdto.getTitle());
        ticket.setStatus(ticketdto.getStatus());
        ticket.setResident(resident);
        ticket.setBuilding(building);

        Ticket savedTicket = ticketRepository.save(ticket);

        return ticketMapper.toResponseDto(savedTicket);
    }

}
