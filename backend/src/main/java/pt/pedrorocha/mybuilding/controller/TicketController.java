package pt.pedrorocha.mybuilding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.pedrorocha.mybuilding.dto.TicketDto;
import pt.pedrorocha.mybuilding.dto.TicketResponseDto;
import pt.pedrorocha.mybuilding.mapper.TicketMapper;
import pt.pedrorocha.mybuilding.model.Building;
import pt.pedrorocha.mybuilding.model.Resident;
import pt.pedrorocha.mybuilding.model.Ticket;
import pt.pedrorocha.mybuilding.services.TicketService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("${api.ticket-path}")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @Autowired
    TicketMapper ticketMapper;

    @RequestMapping(method = RequestMethod.GET, path = {"/list"})
    public ResponseEntity<List<TicketDto>> list() {
        // list all tickets
        List<Ticket> ticketList = ticketService.list();
        List<TicketDto> dtoList  = new ArrayList<>();
        for(Ticket tickets : ticketList){
            dtoList.add(ticketMapper.toDto(tickets));
        }

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/list/building/{id}"})
    public void listByBuilding(@PathVariable long id, @RequestBody Building building) {
        //list all tickets from a specific building
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/list/resident/{id}"})
    public void listByResident(@PathVariable long id, @RequestBody Resident resident) {
        // list all tickets from a specific resident
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/add"})
    public ResponseEntity<TicketResponseDto> add(@RequestBody TicketDto ticketDto) {
        TicketResponseDto ticket = ticketService.add(ticketDto);

        // add a new ticket
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/update/{id}"})
    public void update(@PathVariable long id, @RequestBody Ticket ticket) {
        //update a specific ticket
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/delete/{id}"})
    public void delete(@PathVariable long id) {
        //delete a specific ticket by its id
    }
}
