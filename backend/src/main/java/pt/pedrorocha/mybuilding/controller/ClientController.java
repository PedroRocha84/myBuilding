package pt.pedrorocha.mybuilding.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
        return clientService.loadClients();
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/add"})
    public String addClient(@RequestBody Client client){
        clientService.addClient(client);
        return "Client submitted successfully!";
    }
}
