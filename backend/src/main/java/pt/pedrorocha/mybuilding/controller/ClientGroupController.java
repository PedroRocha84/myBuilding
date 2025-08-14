package pt.pedrorocha.mybuilding.controller;

import org.apache.coyote.Request;
import org.hibernate.service.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pt.pedrorocha.mybuilding.model.ClientGroup;
import pt.pedrorocha.mybuilding.services.ClientGroupService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${api.base-group-path}")
public class ClientGroupController {

    private ClientGroupService clientGroupService;

    public ClientGroupController(ClientGroupService clientGroupService) {this.clientGroupService = clientGroupService;}


    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public List<ClientGroup> list() {
        return clientGroupService.list();
    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/add"})
    public ResponseEntity<Map<String, Object>> add(@RequestBody ClientGroup clientGroup) {

//        Service response = clientGroupService.addClientGroup(clientGroup);
//
//        Map<String, Object> responseBody = new HashMap<>();
//        responseBody.put("message", response.getMessage());
//
//        if (response.isSuccess()) {
//            responseBody.put("data", response.getData());
//            return ResponseEntity.ok(responseBody);
//        } else {
//            return ResponseEntity.badRequest().body(responseBody);
//        }

        return null;
    }
}
