package pt.pedrorocha.mybuilding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.pedrorocha.mybuilding.model.Building;
import pt.pedrorocha.mybuilding.model.Resident;
import pt.pedrorocha.mybuilding.model.Ticket;

@Controller
@RequestMapping("${api.ticket-path}")
public class TicketController {



    @RequestMapping(method = RequestMethod.GET, path = {"/list"})
    public void list() {
        // list all tickets
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
    public void add(@RequestBody Ticket ticket) {
        // add a new ticket

    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/update/{id}"})
    public void update(@PathVariable long id, @RequestBody Ticket ticket) {
        //update a specific ticket
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/delete/{id}"})
    public void delete(@PathVariable long id) {
        //delete a specific ticket by it's id
    }
}
