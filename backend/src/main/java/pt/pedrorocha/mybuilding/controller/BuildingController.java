package pt.pedrorocha.mybuilding.controller;

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
    public List<Building> listBuildings() {
        return buildingService.loadBuildings();
    }

    @RequestMapping(method= RequestMethod.POST, path={"/add"})
    public String addBuilding(@RequestBody Building building) {
        buildingService.addBuilding(building);
        return "Building inserted successfully!";
    }

    @RequestMapping(method= RequestMethod.PUT, path={"/update/{id}"})
    public String updateBuilding(@PathVariable long id, @RequestBody Building building) {
        try {
            buildingService.updateBuilding(id, building);
        } catch (Exception e) {
            return "Building update failed!";
        }
        return "Building updated successfully!";
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/delete"})
    public String deleteBuilding(@RequestParam long id) {
        return buildingService.removeBuilding(id);
    }
}
