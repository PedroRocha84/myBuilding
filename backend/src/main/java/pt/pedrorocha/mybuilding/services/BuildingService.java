package pt.pedrorocha.mybuilding.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.pedrorocha.mybuilding.model.Building;
import pt.pedrorocha.mybuilding.repository.BuildingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BuildingService {

    private final BuildingRepository buildingRepository;

    // Constructor injection
    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    // Get building by id
    public Optional<Building> getBuilding(Long id) {
        return buildingRepository.findById(id);
    }

    // Get All Buildings

    public List<Building> loadBuildings(){
        return new ArrayList<>(buildingRepository.findAll());
    }

    // Add or update building
    @Transactional
    public Building addBuilding(Building building) {
        return buildingRepository.save(building);
    }
}
