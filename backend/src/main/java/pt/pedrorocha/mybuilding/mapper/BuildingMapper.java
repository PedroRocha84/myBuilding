package pt.pedrorocha.mybuilding.mapper;

import org.springframework.stereotype.Service;
import pt.pedrorocha.mybuilding.dto.BuildingDto;
import pt.pedrorocha.mybuilding.model.Building;

@Service
public class BuildingMapper {
    public BuildingDto ToDto(Building building) {
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
}
