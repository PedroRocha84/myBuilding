package pt.pedrorocha.mybuilding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.pedrorocha.mybuilding.dto.TicketDto;
import pt.pedrorocha.mybuilding.dto.TicketResponseDto;
import pt.pedrorocha.mybuilding.mapper.TicketMapper;
import pt.pedrorocha.mybuilding.entity.Ticket;
import pt.pedrorocha.mybuilding.services.ClientGroupService;
import pt.pedrorocha.mybuilding.services.TicketService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${api.ticket-path}")
public class TicketController {

    private final TicketService ticketService;

    private final TicketMapper ticketMapper;

    public TicketController(TicketService ticketService, TicketMapper ticketMapper) {
        this.ticketService = ticketService;
        this.ticketMapper = ticketMapper;

    }

    // Get all tickets
    @GetMapping
    public ResponseEntity<List<TicketResponseDto>> getAllTickets() {
        // list all tickets
        List<Ticket> ticketList = ticketService.list();
        List<TicketResponseDto> dtoList  = new ArrayList<>();

        for(Ticket tickets : ticketList){
            dtoList.add(ticketMapper.toResponseDto(tickets));
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    // Get all tickets for a building
    @GetMapping("/buildings/{buildingId}")
    public ResponseEntity<List<TicketResponseDto>> getTicketsByBuilding(@PathVariable long buildingId) {
        //list all tickets from a specific building
        List<TicketResponseDto> ticketList = ticketService.findByBuildingId(buildingId)
                .stream()
                .map(dto -> ticketMapper.toResponseDto(dto))
                .toList();
        return new ResponseEntity<>(ticketList, HttpStatus.OK);
    }

    // Get all tickets for a resident
    @GetMapping("/residents/{residentId}")
    public ResponseEntity<List<TicketResponseDto>> getTicketsByResident(@PathVariable long residentId) {
        // list all tickets from a specific resident
        List<TicketResponseDto> ticketList = ticketService.findByResidentId(residentId)
                .stream()
                .map(dto -> ticketMapper.toResponseDto(dto))
                .toList();
        return new ResponseEntity<>(ticketList, HttpStatus.OK);
    }

    // Create a new ticket
    @PostMapping
    public ResponseEntity<TicketResponseDto> createTicket(@RequestBody TicketDto ticketDto) {
        // add a new ticket
        TicketResponseDto ticket = ticketService.add(ticketDto);
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketResponseDto> updateTicket(@PathVariable long id, @RequestBody TicketDto ticketDto) {
        //update a specific ticket
        Ticket updatedTicket = ticketService.update(id, ticketDto);

        return new ResponseEntity<>(ticketMapper.toResponseDto(updatedTicket), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        ticketService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
