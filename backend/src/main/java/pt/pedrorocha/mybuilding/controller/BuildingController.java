package pt.pedrorocha.mybuilding.controller;

import org.springframework.web.bind.annotation.*;
import pt.pedrorocha.mybuilding.model.Building;
import pt.pedrorocha.mybuilding.services.BuildingService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.base-building-path}")
public class BuildingController {

    BuildingService buildingService;

    public  BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping("/api/test")
    public String sayHello() {
        return "Hello, Spring Boot!";
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

}
