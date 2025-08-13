package pt.pedrorocha.mybuilding.controller;


import org.springframework.web.bind.annotation.*;
import pt.pedrorocha.mybuilding.model.Company;
import pt.pedrorocha.mybuilding.services.CompanyService;

import java.util.List;

@RestController
@RequestMapping("${api.base-clients-path}")
public class ClientController {

    CompanyService companyService;

    public ClientController(CompanyService clientsService){this.companyService = clientsService;}

    @RequestMapping(method = RequestMethod.GET, path =  {"/", ""})
    public List<Company> list(){
        return companyService.list();
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/add"})
    public String addClient(@RequestBody Company company){
        companyService.add(company);
        return "Client submitted successfully!";
    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/update/{id}"})
    public String updateClient(@RequestBody Company company, @PathVariable Long id){
        try{
            companyService.update(id, company);
        } catch (Exception e) {
            return "Client update failed!";
        }
        return "Client updated successfully!";
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/delete/{id}"})
    public String deleteClient(@PathVariable Long id){
        if(companyService.getClientByID(id)){
            companyService.delete(id);
            return "Client deleted successfully!";
        }
        return "Client delete failed!";
    }
}
