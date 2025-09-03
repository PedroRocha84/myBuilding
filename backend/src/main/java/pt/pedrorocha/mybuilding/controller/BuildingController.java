package pt.pedrorocha.mybuilding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pt.pedrorocha.mybuilding.dto.BuildingDto;
import pt.pedrorocha.mybuilding.mapper.BuildingMapper;
import pt.pedrorocha.mybuilding.model.Building;
import pt.pedrorocha.mybuilding.repository.BuildingRepository;
import pt.pedrorocha.mybuilding.services.BuildingService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${api.base-building-path}")
public class BuildingController {

    @Autowired
    BuildingService buildingService;

    @Autowired
    BuildingMapper buildingMapper;

    @Autowired
    BuildingRepository buildingRepository;

    @RequestMapping(method=RequestMethod.GET, path = {"/", "", "/list"})
    public ResponseEntity<List<BuildingDto>> list() {
        try {
            List<Building> buildingList = buildingService.list();
            List<BuildingDto> dtoList = new ArrayList<>();
            for(Building building : buildingList){
                dtoList.add(buildingMapper.toDto(building));
            }

            return  new ResponseEntity<>(dtoList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/add", "/add/"})
    @PostMapping({"/add", "/add/"})
    public ResponseEntity<BuildingDto> addBuilding(@RequestBody BuildingDto buildingDto) {
        if (buildingRepository.existsByName(buildingDto.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        BuildingDto savedBuilding = buildingService.add(buildingDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBuilding);
    }


    @RequestMapping(method= RequestMethod.PUT, path={"/update/{id}"})
    public ResponseEntity<String> updateBuilding(@PathVariable long id, @RequestBody BuildingDto buildingDto) {
        try {
            buildingService.update(id, buildingDto);
            return new  ResponseEntity<>("Building " + buildingDto.getName() + " updated", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating building: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/delete/{id}"})
    public ResponseEntity<BuildingDto> deleteBuilding(@PathVariable long id) {
           Building building = buildingRepository.findById(id)
                   .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

           BuildingDto dto = buildingMapper.toDto(building);

           buildingRepository.delete(building);

           return ResponseEntity.ok(dto);

    }
}
