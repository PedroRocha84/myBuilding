package pt.pedrorocha.mybuilding.controller;


import org.springframework.web.bind.annotation.*;
import pt.pedrorocha.mybuilding.model.Client;
import pt.pedrorocha.mybuilding.services.ClientService;

import java.util.List;

@RestController
@RequestMapping("${api.base-clients-path}")
public class ClientController {

    ClientService clientService;

    public ClientController(ClientService clientsService){this.clientService = clientsService;}

    @RequestMapping(method = RequestMethod.GET, path =  {"/", ""})
    public List<Client> getClients(){
        return clientService.list();
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/add"})
    public String addClient(@RequestBody Client client){
        clientService.add(client);
        return "Client submitted successfully!";
    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/update/{id}"})
    public String updateClient(@RequestBody Client client, @PathVariable Long id){
        try{
            clientService.update(id, client);
        } catch (Exception e) {
            return "Client update failed!";
        }
        return "Client updated successfully!";
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/delete/{id}"})
    public String deleteClient(@PathVariable Long id){
        if(clientService.getClientByID(id)){
            clientService.delete(id);
            return "Client deleted successfully!";
        }
        return "Client delete failed!";
    }
}
