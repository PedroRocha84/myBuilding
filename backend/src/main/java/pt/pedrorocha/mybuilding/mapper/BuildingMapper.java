package pt.pedrorocha.mybuilding.mapper;

import org.springframework.stereotype.Service;
import pt.pedrorocha.mybuilding.dto.BuildingDto;
import pt.pedrorocha.mybuilding.dto.BuildingResponseDto;
import pt.pedrorocha.mybuilding.entity.Building;

@Service
public class BuildingMapper {
    public BuildingDto toDto(Building building) {
        if(building == null) {return null;}

        BuildingDto dto = new BuildingDto();
        dto.setName(building.getName());
        dto.setDescription(building.getDescription());
        dto.setAlias(building.getAlias());
        dto.setCity(building.getCity());
        dto.setCountry(building.getCountry());
        dto.setDistrict(building.getDistrict());
        dto.setStreet(building.getStreet());
        dto.setPostCode(building.getPostCode());
        dto.setVatNumber(building.getVatNumber());

        return dto;
    }

    public Building toEntity(BuildingDto buildingDto) {
        if(buildingDto == null) {return null;}

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

        return building;
    }

    public BuildingResponseDto toResponseDto(Building building) {
        if(building == null) {return null;}

        BuildingResponseDto dto = new BuildingResponseDto();

        dto.setId(building.getId());
        dto.setName(building.getName());
        dto.setDescription(building.getDescription());
        dto.setAlias(building.getAlias());
        dto.setCity(building.getCity());
        dto.setCountry(building.getCountry());
        dto.setDistrict(building.getDistrict());
        dto.setPostCode(building.getPostCode());
        dto.setStreet(building.getStreet());
        dto.setVatNumber(building.getVatNumber());

        return dto;
    }

}
