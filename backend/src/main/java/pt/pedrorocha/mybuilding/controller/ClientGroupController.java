package pt.pedrorocha.mybuilding.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.pedrorocha.mybuilding.entity.ClientGroup;
import pt.pedrorocha.mybuilding.services.ClientGroupService;

import java.util.List;

@RestController
@RequestMapping("${api.base-group-path}")
public class ClientGroupController {

    private ClientGroupService clientGroupService;

    public ClientGroupController(ClientGroupService clientGroupService) {this.clientGroupService = clientGroupService;}

    @RequestMapping(method=RequestMethod.GET, path = {"/", "", "/list"})
    public ResponseEntity<List<ClientGroup>> list() {
        try{
            return new ResponseEntity<>(clientGroupService.list(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/add", "/add/"})
    public ResponseEntity<String> add(@RequestBody ClientGroup clientGroup) {
        try{
            clientGroupService.add(clientGroup);
            return new  ResponseEntity<>("Client Group added", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Some error occur, please try again.", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/update/{id}"})
    public ResponseEntity<String> update(@PathVariable long id, @RequestBody ClientGroup clientGroup) {
        try{
            clientGroupService.update(id, clientGroup);
            return new  ResponseEntity<>("Client Group " + clientGroup.getName() + " updated", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/delete/{id}"})
    public ResponseEntity<String> delete(@PathVariable long id) {
        try {
            clientGroupService.delete(id);
            return new  ResponseEntity<>("Client group deleted", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
        }
    }
}
