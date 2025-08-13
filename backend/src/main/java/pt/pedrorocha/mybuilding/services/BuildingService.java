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

    // Add
    @Transactional
    public void addBuilding(Building building) {
        buildingRepository.save(building);
    }

    // Update
    @Transactional
    public void updateBuilding(long id, Building building) {
        buildingRepository.findById(id)
                .map(exist -> {
                    exist.setName(building.getName());
                    exist.setAlias(building.getAlias());
                    exist.setDescription(building.getDescription());
                    exist.setDistrict(building.getDistrict());
                    exist.setVatNumber(building.getVatNumber());
                    exist.setCity(building.getCity());
                    exist.setCountry(building.getCountry());
                    exist.setPostCode(building.getPostCode());
                    exist.setStreet(building.getStreet());

                    return buildingRepository.save(exist);
                })
                .orElseThrow(() -> new RuntimeException("Building not found!"));
    }

    @Transactional
    public String removeBuilding(long idBuilding) {
        if(getBuilding(idBuilding).isPresent()){
            buildingRepository.deleteById(idBuilding);
            return "Building " + idBuilding + " removed";
        }
        return "Building " + idBuilding + " not found";
    }
}
