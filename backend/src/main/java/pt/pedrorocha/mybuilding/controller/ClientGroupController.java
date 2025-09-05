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

    private final ClientGroupService clientGroupService;

    public ClientGroupController(ClientGroupService clientGroupService) {this.clientGroupService = clientGroupService;}

    @GetMapping
    public ResponseEntity<List<ClientGroup>> getAllGroups() {
         return new ResponseEntity<>(clientGroupService.list(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientGroup> createClientGroups(@RequestBody ClientGroup clientGroup) {
            clientGroupService.add(clientGroup);
            return new ResponseEntity<>(clientGroup, HttpStatus.OK);
    }

    @PutMapping("/{clientGroupId}")
    public ResponseEntity<String> update(@PathVariable long clientGroupId, @RequestBody ClientGroup clientGroup) {
        clientGroupService.update(clientGroupId, clientGroup);
        return new ResponseEntity<>("Client group " + clientGroup.getName() + " updated", HttpStatus.OK);
    }

    @DeleteMapping("/{clientGroupId}")
    public ResponseEntity<String> delete(@PathVariable long clientGroupId) {
        clientGroupService.delete(clientGroupId);
        return new ResponseEntity<>("Client group " + clientGroupId + " deleted", HttpStatus.OK);
    }
}
