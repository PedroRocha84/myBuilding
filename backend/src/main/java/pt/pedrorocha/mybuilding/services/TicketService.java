package pt.pedrorocha.mybuilding.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.pedrorocha.mybuilding.dto.TicketDto;
import pt.pedrorocha.mybuilding.dto.TicketResponseDto;
import pt.pedrorocha.mybuilding.mapper.TicketMapper;
import pt.pedrorocha.mybuilding.entity.Building;
import pt.pedrorocha.mybuilding.entity.Resident;
import pt.pedrorocha.mybuilding.entity.Ticket;
import pt.pedrorocha.mybuilding.repository.BuildingRepository;
import pt.pedrorocha.mybuilding.repository.ResidentRepository;
import pt.pedrorocha.mybuilding.repository.TicketRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TicketService {

    private final  TicketRepository ticketRepository;
    private final  ResidentRepository residentRepository;
    private final  BuildingRepository buildingRepository;
    private final  TicketMapper ticketMapper;
    private final  ResidentService residentService;
    private final BuildingService buildingService;

    public TicketService(TicketRepository ticketRepository,ResidentRepository residentRepository, BuildingRepository buildingRepository, TicketMapper ticketMapper, ResidentService residentService, BuildingService buildingService) {
        this.ticketRepository = ticketRepository;
        this.residentRepository = residentRepository;
        this.buildingRepository = buildingRepository;
        this.ticketMapper = ticketMapper;
        this.residentService = residentService;
        this.buildingService = buildingService;
    }

    public List<Ticket> list(){
        return new ArrayList<>(ticketRepository.findAll());
    }

    public TicketResponseDto add(TicketDto ticketdto) throws ResponseStatusException {
        if(ticketdto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket can't be null");
        }

        Resident resident = residentRepository.findById(ticketdto.getResidentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resident not found"));

        Building building = buildingRepository.findById(ticketdto.getBuildingId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Building not found"));

        if(!Objects.equals(resident.getBuilding().getId(), building.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resident and Building not found");
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

    public void delete(long id){
        if (residentRepository.findById(id).isPresent()) {
            residentRepository.deleteById(id);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Resident not found");
        }
    }
    public List<Ticket> findByBuildingId(Long buildingId){
        return ticketRepository.findByBuildingId(buildingId);
    }
    public List<Ticket> findByResidentId(Long residentId){
        return ticketRepository.findByResidentId(residentId);
    }
}
