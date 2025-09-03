package pt.pedrorocha.mybuilding.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.Objects;

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

    @Autowired
    private TicketService ticketService;
    @Autowired
    private ResidentService residentService;
    @Autowired
    private BuildingService buildingService;

    public List<Ticket> list(){
        return new ArrayList<>(ticketRepository.findAll());
    }

    public TicketResponseDto add(TicketDto ticketdto) throws ResponseStatusException {
        if(ticketdto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket can't be null");
        }

        Resident resident = residentRepository.findById(ticketdto.getResidentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Resident not found"));

        Building building = buildingRepository.findById(ticketdto.getBuildingId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Building not found"));

        if(!Objects.equals(resident.getBuilding().getId(), building.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Resident and Building not found");
        }

        Ticket ticket = ticketMapper.toEntity(ticketdto, resident, building);

        Ticket savedTicket = ticketRepository.save(ticket);

        return ticketMapper.toResponseDto(savedTicket);
    }

    public Ticket update(Long id, TicketDto ticketDto){
        if(ticketDto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket can't be null");
        }

        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket not found"));

        Building building = buildingService.findById(ticketDto.getBuildingId());
        Resident resident = residentService.findById(ticketDto.getResidentId());

        // ✅ update only mutable fields
        existingTicket.setTitle(ticketDto.getTitle());
        existingTicket.setDescription(ticketDto.getDescription());
        existingTicket.setStatus(ticketDto.getStatus());
        existingTicket.setBuilding(building);
        existingTicket.setResident(resident);

        return ticketRepository.save(existingTicket);
    }
}
