package pt.pedrorocha.mybuilding.controller;

import ch.qos.logback.core.net.server.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pt.pedrorocha.mybuilding.dto.ResidentDto;
import pt.pedrorocha.mybuilding.model.ClientGroup;
import pt.pedrorocha.mybuilding.model.Resident;
import pt.pedrorocha.mybuilding.repository.ClientGroupRepository;
import pt.pedrorocha.mybuilding.services.ClientGroupService;
import pt.pedrorocha.mybuilding.services.ResidentService;

import java.util.List;

@RestController
@RequestMapping("${api.base-resident-path}")
public class ResidentController {

    ResidentService residentService;
    ClientGroupService clientGroupService;

    public ResidentController(ResidentService residentService, ClientGroupRepository clientGroupRepository, ClientGroupService clientGroupService) {
        this.residentService = residentService;
        this.clientGroupService = clientGroupService;
    }

    @RequestMapping(method=RequestMethod.GET, path = {"/", "", "/list"})
    public ResponseEntity<List<Resident>> list(){
        try {
            return new ResponseEntity<>(residentService.list(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/add", "/add/"})
    public ResponseEntity<String> addResident(@RequestBody ResidentDto dto){
        try {
            Resident resident = new Resident();
            resident.setFirstName(dto.getFirstName());
            resident.setLastName(dto.getLastName());
            if(dto.getClientGroupId() != null){
                resident.setClientGroup(clientGroupService.findById(dto.getClientGroupId()));
            }else{
                resident.setClientGroup(null);
            }
            residentService.add(resident);

            return new ResponseEntity<>("Resident " + dto.getFirstName() + " added successfully", HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
