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

    @RequestMapping(method=RequestMethod.GET, path = {"/list"})
    public ResponseEntity<List<Building>> list() {
        try {
            return new ResponseEntity<>(buildingService.list(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method= RequestMethod.POST, path={"/add"})
    public ResponseEntity<String> addBuilding(@RequestBody Building building) {
        try{
           buildingService.add(building);
           return ResponseEntity.ok("Building added successfully");
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding building: " + e.getMessage());
        }
    }

    @RequestMapping(method= RequestMethod.PUT, path={"/update/{id}"})
    public ResponseEntity<String> updateBuilding(@PathVariable long id, @RequestBody Building building) {
        try {
            buildingService.update(id, building);
            return ResponseEntity.ok("Building updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating building: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/delete"})
    public ResponseEntity<String> deleteBuilding(@RequestParam long id) {
        try {
            buildingService.delete(id);
            return ResponseEntity.ok("Building deleted successfully");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting building: " + e.getMessage());
        }
    }
}
