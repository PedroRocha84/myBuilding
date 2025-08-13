package pt.pedrorocha.mybuilding.controller;

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


    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public List<ClientGroup> list() {
        return clientGroupService.list();
    }
}
