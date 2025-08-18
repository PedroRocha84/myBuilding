package pt.pedrorocha.mybuilding.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pt.pedrorocha.mybuilding.model.ClientGroup;
import pt.pedrorocha.mybuilding.services.ClientGroupService;

import java.util.List;

@RestController
@RequestMapping("${api.base-group-path}")
public class ClientGroupController {

    private ClientGroupService clientGroupService;

    public ClientGroupController(ClientGroupService clientGroupService) {this.clientGroupService = clientGroupService;}


    @RequestMapping(method=RequestMethod.GET, path = {"/", "", "/list"})
    public List<ClientGroup> list() {
        return clientGroupService.list();
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/add", "/add/"})
    public ResponseEntity<String> add(@RequestBody ClientGroup clientGroup) {
        try{
            clientGroupService.addClientGroup(clientGroup);
            return new  ResponseEntity<>("OK", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/update", "/update/"})
    public ResponseEntity<String> update(@RequestBody ClientGroup clientGroup) {
        try{
            clientGroupService.updateClientGroup(clientGroup);
            return new  ResponseEntity<>("OK", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
        }
    }
}
