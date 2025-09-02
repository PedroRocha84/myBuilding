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
    public BuildingDto add(BuildingDto buildingDto) {
        if(buildingDto == null) {return null;}

        Building building = new Building();
        building.setId(buildingDto.getId());
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

        return buildingMapper.toDto(savedBuilding);

    }

    @Transactional
    public BuildingDto update(long id, BuildingDto buildingDto) {

        Building existingBuilding = buildingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Building not found with id " + id));

        existingBuilding.setId(buildingDto.getId());
        existingBuilding.setName(buildingDto.getName());
        existingBuilding.setDescription(buildingDto.getDescription());
        existingBuilding.setAlias(buildingDto.getAlias());
        existingBuilding.setCity(buildingDto.getCity());
        existingBuilding.setCountry(buildingDto.getCountry());
        existingBuilding.setDistrict(buildingDto.getDistrict());
        existingBuilding.setPostCode(buildingDto.getPostCode());
        existingBuilding.setStreet(buildingDto.getStreet());
        existingBuilding.setVatNumber(buildingDto.getVatNumber());

        Building updatedBuilding = buildingRepository.save(existingBuilding);

        return buildingMapper.toDto(updatedBuilding);
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
