package pt.pedrorocha.mybuilding.services;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pt.pedrorocha.mybuilding.dto.BuildingDto;
import pt.pedrorocha.mybuilding.dto.BuildingResponseDto;
import pt.pedrorocha.mybuilding.mapper.BuildingMapper;
import pt.pedrorocha.mybuilding.entity.Building;
import pt.pedrorocha.mybuilding.repository.BuildingRepository;
import pt.pedrorocha.mybuilding.repository.ResidentRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class BuildingService {

    @Autowired
    BuildingRepository buildingRepository;

   @Autowired
    BuildingMapper buildingMapper;

   @Autowired
    ResidentRepository residentRepository;

    public List<Building> list(){
        return new ArrayList<>(buildingRepository.findAll());
    }

    @Transactional
    public BuildingResponseDto add(BuildingDto buildingDto) {
        if(buildingDto == null) {
            return null;
        }

        if(buildingRepository.existsByName(buildingDto.getName())) {
            throw new EntityExistsException("Building already exists");
        }

        Building building = buildingMapper.toEntity(buildingDto);

        Building savedBuilding = buildingRepository.save(building);

        return buildingMapper.toResponseDto(savedBuilding);

    }

    @Transactional
    public BuildingResponseDto update(Building building) {

        if(!buildingRepository.existsById(building.getId())) {
            return null;
        }

        Building existingBuilding = buildingRepository.findById(building.getId()).get();
        existingBuilding.setName(building.getName());
        existingBuilding.setAlias(building.getAlias());
        existingBuilding.setVatNumber(building.getVatNumber());
        existingBuilding.setDescription(building.getDescription());
        existingBuilding.setStreet(building.getStreet());
        existingBuilding.setPostCode(building.getPostCode());
        existingBuilding.setCity(building.getCity());
        existingBuilding.setCountry(building.getCountry());
        existingBuilding.setDistrict(building.getDistrict());

        return buildingMapper.toResponseDto(existingBuilding);
    }

    @Transactional
    public void delete(long buildingId) {
        Building building = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new EntityNotFoundException("Building not found!"));

        // Is not possible to delete a building if exists a resident leaving there.
        // Alter DB schema foreign keys

        if (!residentRepository.findByBuildingId(buildingId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot delete building with residents");
        }

        buildingRepository.deleteById(buildingId);

    }

    @Transactional(readOnly = true)
    public Building findById(Long id) {
        return buildingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Building not found with id: " + id));
    }
}
