package pt.pedrorocha.mybuilding.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.pedrorocha.mybuilding.dto.BuildingDto;
import pt.pedrorocha.mybuilding.mapper.BuildingMapper;
import pt.pedrorocha.mybuilding.model.Building;
import pt.pedrorocha.mybuilding.repository.BuildingRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class BuildingService {

    @Autowired
    BuildingRepository buildingRepository;

   @Autowired
    BuildingMapper buildingMapper;

    public List<Building> list(){
        return new ArrayList<>(buildingRepository.findAll());
    }

    @Transactional
    public BuildingDto add(BuildingDto buildingDto) git ad{
        Building building = new Building();
        building.setName(buildingDto.getName());
        building.setDescription(buildingDto.getDescription());
        building.setAlias(buildingDto.getAlias());
        building.setCity(buildingDto.getCity());
        building.setCountry(buildingDto.getCountry());
        building.setDistrict(buildingDto.getDistrict());
        building.setPostCode(buildingDto.getPostCode());
        building.setStreet(buildingDto.getStreet());
        building.setVatNumber(buildingDto.getVatNumber());

        Building savedBuilding = buildingRepository.save(building);

        return buildingMapper.ToDto(savedBuilding);

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

    @Transactional(readOnly = true)
    public Building findById(Long id) {
        return buildingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Building not found with id: " + id));
    }
}
