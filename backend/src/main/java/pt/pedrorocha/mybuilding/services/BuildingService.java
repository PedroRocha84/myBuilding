package pt.pedrorocha.mybuilding.services;

import jakarta.persistence.EntityNotFoundException;
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

    public List<Building> list(){
        return new ArrayList<>(buildingRepository.findAll());
    }

    @Transactional
    public void add(Building building) {
        String name = building.getName();

        if(buildingRepository.existsByName(name)){
            throw new IllegalArgumentException("Name already exists");
        }
        buildingRepository.save(building);

    }

    @Transactional
    public void update(long id, Building building) {
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
    public void delete(long idBuilding) {
        Building building = buildingRepository.findById(idBuilding)
                .orElseThrow(() -> new EntityNotFoundException("Building not found!"));
        buildingRepository.delete(building);
    }
}
