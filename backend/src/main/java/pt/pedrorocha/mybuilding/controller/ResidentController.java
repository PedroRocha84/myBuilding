package pt.pedrorocha.mybuilding.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pt.pedrorocha.mybuilding.dto.ResidentDto;
import pt.pedrorocha.mybuilding.entity.Ticket;
import pt.pedrorocha.mybuilding.mapper.ResidentMapper;
import pt.pedrorocha.mybuilding.entity.Building;
import pt.pedrorocha.mybuilding.entity.Resident;
import pt.pedrorocha.mybuilding.repository.ResidentRepository;
import pt.pedrorocha.mybuilding.repository.TicketRepository;
import pt.pedrorocha.mybuilding.services.BuildingService;
import pt.pedrorocha.mybuilding.services.ResidentService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${api.base-resident-path}")
public class ResidentController {

    private final ResidentMapper residentMapper;
    private final ResidentRepository residentRepository;
    private final ResidentService residentService;
    private final BuildingService buildingService;
    private final TicketRepository ticketRepository;

    public ResidentController(ResidentMapper residentMapper, ResidentRepository residentRepository, BuildingService buildingService, ResidentService residentService, TicketRepository ticketRepository) {
        this.residentMapper = residentMapper;
        this.residentRepository = residentRepository;
        this.buildingService = buildingService;
        this.residentService = residentService;
        this.ticketRepository = ticketRepository;
    }

    // List all residents
    @GetMapping
    public ResponseEntity<List<ResidentDto>> getAllResidents(){
        List<Resident> residentList = residentService.list();
        List<ResidentDto> residentDtoList = new ArrayList<>();
        for(Resident resident : residentList){
           residentDtoList.add(residentMapper.ToDto(resident));
        }
        return  new ResponseEntity<>(residentDtoList, HttpStatus.OK);

    }

    // Add a new resident
    @PostMapping
    public ResponseEntity<ResidentDto> add(@RequestBody ResidentDto residentDto){
        // Fetch the building
        Building building = buildingService.findById(residentDto.getBuildingId());

        // Call the service to add the resident
        ResidentDto savedResident = residentService.add(residentDto, building);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedResident);

    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/update/{id}"})
    public ResponseEntity<ResidentDto> updateResident(@PathVariable Long id, @RequestBody ResidentDto dto){
            ResidentDto updatedResident = residentService.update(id, dto);
            return ResponseEntity.ok(updatedResident);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/delete/{id}"})
    public ResponseEntity<ResidentDto> deleteResident(@PathVariable Long id){

        // Check if the resident exists
        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // Map the entity to DTO before deletion
        ResidentDto dto = residentMapper.ToDto(resident);

        // Delete the resident
        residentRepository.deleteById(id);

        // Return the DTO with 200 OK
        return ResponseEntity.ok(dto);
    }
}