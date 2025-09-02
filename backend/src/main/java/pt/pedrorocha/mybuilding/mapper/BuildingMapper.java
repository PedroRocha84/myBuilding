package pt.pedrorocha.mybuilding.mapper;

import org.springframework.stereotype.Service;
import pt.pedrorocha.mybuilding.dto.BuildingDto;
import pt.pedrorocha.mybuilding.model.Building;

@Service
public class BuildingMapper {
    public BuildingDto toDto(Building building) {
        if(building == null) {return null;}

        BuildingDto dto = new BuildingDto();
        dto.setId(building.getId());
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

    public Building toEntity(BuildingDto dto) {
        if(dto == null) {return null;}
        Building building = new Building();
        building.setId(dto.getId());
        building.setName(dto.getName());
        building.setDescription(dto.getDescription());
        building.setAlias(dto.getAlias());
        building.setCity(dto.getCity());
        building.setCountry(dto.getCountry());
        building.setDistrict(dto.getDistrict());
        building.setStreet(dto.getStreet());
        building.setPostCode(dto.getPostCode());
        building.setVatNumber(dto.getVatNumber());

        return building;
    }
}
