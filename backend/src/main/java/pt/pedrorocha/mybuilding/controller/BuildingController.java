package pt.pedrorocha.mybuilding.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.pedrorocha.mybuilding.model.Building;
import pt.pedrorocha.mybuilding.services.BuildingService;

import java.util.List;

@RestController
@RequestMapping("${api.base-building-path}")
public class BuildingController {

    BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @RequestMapping(method=RequestMethod.GET, path = {"/", "", "/list"})
    public ResponseEntity<List<Building>> list() {
        try {
            return new ResponseEntity<>(buildingService.list(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/add", "/add/"})
    public ResponseEntity<String> addBuilding(@RequestBody Building building) {
        try{
           buildingService.add(building);
           return new  ResponseEntity<>("Building added", HttpStatus.OK);
        } catch (Exception e){
           return new ResponseEntity<>("Some error occur, please try again.", HttpStatus.BAD_REQUEST);
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
