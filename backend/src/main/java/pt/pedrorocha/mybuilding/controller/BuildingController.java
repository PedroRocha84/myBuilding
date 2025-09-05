package pt.pedrorocha.mybuilding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.pedrorocha.mybuilding.dto.BuildingDto;
import pt.pedrorocha.mybuilding.dto.BuildingResponseDto;
import pt.pedrorocha.mybuilding.mapper.BuildingMapper;
import pt.pedrorocha.mybuilding.entity.Building;
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


    @GetMapping
    public ResponseEntity<List<BuildingResponseDto>> getAllBuildings() {
        try {
            List<Building> buildingList = buildingService.list();
            List<BuildingResponseDto> dtoList = new ArrayList<>();
            for(Building building : buildingList){
                dtoList.add(buildingMapper.toResponseDto(building));
            }

            return  new ResponseEntity<>(dtoList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<BuildingResponseDto> createBuildings(@RequestBody BuildingDto buildingDto) {
        BuildingResponseDto savedBuilding = buildingService.add(buildingDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBuilding);
    }

    @PutMapping("/{buildingId}")
    public ResponseEntity<String> updateBuilding(@RequestBody Building building) {
        buildingService.update(building);
        return  ResponseEntity.status(HttpStatus.OK).body("Building id " + building.getId().toString() + " updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBuilding(@PathVariable long id) {
        String buildingName = buildingService.findById(id).getName();
        buildingService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Building " + buildingName + " deleted.");

    }
}
