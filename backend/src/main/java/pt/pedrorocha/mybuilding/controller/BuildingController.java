package pt.pedrorocha.mybuilding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
                dtoList.add(buildingMapper.ToDto(building));
            }

            return  new ResponseEntity<>(dtoList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/add", "/add/"})
    public ResponseEntity<BuildingDto> addBuilding(@RequestBody BuildingDto buildingDto) {
        try{
            if(buildingRepository.existsById((long) buildingDto.getId())){
                return  ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(null);
            }

            BuildingDto savedBuilding = buildingService.add(buildingDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedBuilding);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method= RequestMethod.PUT, path={"/update/{id}"})
    public ResponseEntity<String> updateBuilding(@PathVariable long id, @RequestBody Building building) {
        try {
            buildingService.update(id, building);
            return new  ResponseEntity<>("Building " + building.getName() + " updated", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating building: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/delete/{id}"})
    public ResponseEntity<String> deleteBuilding(@PathVariable long id) {
        try {
            buildingService.delete(id);
            return new  ResponseEntity<>("Building deleted", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
        }
    }
}
