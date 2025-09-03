package pt.pedrorocha.mybuilding.mapper;

import org.springframework.stereotype.Service;
import pt.pedrorocha.mybuilding.dto.TicketDto;
import pt.pedrorocha.mybuilding.dto.TicketResponseDto;
import pt.pedrorocha.mybuilding.model.Building;
import pt.pedrorocha.mybuilding.model.Resident;
import pt.pedrorocha.mybuilding.model.Ticket;

@Service
public class TicketMapper {

    public TicketDto toDto(Ticket ticket) {
        if(ticket == null) {return null;}

        TicketDto ticketDto = new TicketDto();

        ticketDto.setTitle(ticket.getTitle());
        ticketDto.setStatus(ticket.getStatus());
        ticketDto.setDescription(ticket.getDescription());
        ticketDto.setBuildingId(ticket.getBuilding().getId());
        ticketDto.setResidentId(ticket.getResident().getId());

        return ticketDto;
    }

    public TicketResponseDto toResponseDto(Ticket ticket) {
        TicketResponseDto dto = new TicketResponseDto();
        dto.setId(ticket.getId());
        dto.setTitle(ticket.getTitle());
        dto.setDescription(ticket.getDescription());
        dto.setStatus(ticket.getStatus());
        dto.setResidentId(ticket.getResident().getId());
        dto.setBuildingId(ticket.getBuilding().getId());

        return dto;
    }

    public Ticket toEntity(TicketDto dto, Resident resident, Building building) {
        if (dto == null) { return null; }

        Ticket ticket = new Ticket();
        ticket.setTitle(dto.getTitle());
        ticket.setDescription(dto.getDescription());
        ticket.setStatus(dto.getStatus());
        ticket.setResident(resident);
        ticket.setBuilding(building);
        return ticket;
    }
}
