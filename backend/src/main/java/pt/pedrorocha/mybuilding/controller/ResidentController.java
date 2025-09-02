package pt.pedrorocha.mybuilding.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.pedrorocha.mybuilding.dto.ResidentDto;
import pt.pedrorocha.mybuilding.mapper.ResidentMapper;
import pt.pedrorocha.mybuilding.model.Building;
import pt.pedrorocha.mybuilding.model.ClientGroup;
import pt.pedrorocha.mybuilding.model.Resident;
import pt.pedrorocha.mybuilding.repository.ClientGroupRepository;
import pt.pedrorocha.mybuilding.services.BuildingService;
import pt.pedrorocha.mybuilding.services.ClientGroupService;
import pt.pedrorocha.mybuilding.services.ResidentService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${api.base-resident-path}")
public class ResidentController {

    private final ResidentMapper residentMapper;
    ResidentService residentService;
    BuildingService buildingService;
    ClientGroupService clientGroupService;

    public ResidentController(ResidentService residentService, ClientGroupRepository clientGroupRepository, ClientGroupService clientGroupService, BuildingService buildingService, ResidentMapper residentMapper) {
        this.residentService = residentService;
        this.clientGroupService = clientGroupService;
        this.buildingService = buildingService;
        this.residentMapper = residentMapper;
    }

    // List all residents
    @RequestMapping(method=RequestMethod.GET, path = {"/", "", "/list"})
    public ResponseEntity<List<ResidentDto>> list(){
        try {

            List<Resident> residentList = residentService.list();
            List<ResidentDto> residentDtoList = new ArrayList<>();
            for(Resident resident : residentList){
                residentDtoList.add(residentMapper.ToDto(resident));
            }

            return  new ResponseEntity<>(residentDtoList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Add a new resident
    @RequestMapping(method = RequestMethod.POST, path = {"/add", "/add/"})
    public ResponseEntity<ResidentDto> add(@RequestBody ResidentDto residentDto){
        try {
            // Check if buildingId is provided
            if(residentDto.getBuildingId() == null){
                return ResponseEntity
                        .badRequest()
                        .body(null); // or return a message DTO
            }

            // Fetch the building
            Building building = buildingService.findById(residentDto.getBuildingId());

            // Call the service to add the resident
            ResidentDto savedResident = residentService.add(residentDto, building);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedResident);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/update/{id}"})
    public ResponseEntity<ResidentDto> updateResident(@PathVariable Long id, @RequestBody ResidentDto dto){
            ResidentDto updatedResident = residentService.update(id, dto);
            return ResponseEntity.ok(updatedResident);
    }
}