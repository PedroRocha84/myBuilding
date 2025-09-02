package pt.pedrorocha.mybuilding.mapper;

import org.springframework.stereotype.Service;
import pt.pedrorocha.mybuilding.dto.ResidentDto;
import pt.pedrorocha.mybuilding.model.Resident;

@Service
public class ResidentMapper {
    public ResidentDto ToDto(Resident resident) {
        if(resident == null) {return null;}

        ResidentDto dto = new ResidentDto();
        dto.setFirstName(resident.getFirstName());
        dto.setLastName(resident.getLastName());
        dto.setFraction(resident.getFraction());
        dto.setBuildingId((long) resident.getBuilding().getId());

        if (resident.getClientGroup() != null) {
            dto.setClientGroupId((long) resident.getClientGroup().getId());
        }
        return dto;
    }
}
