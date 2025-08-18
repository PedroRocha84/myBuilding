package pt.pedrorocha.mybuilding.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pt.pedrorocha.mybuilding.model.Resident;
import pt.pedrorocha.mybuilding.repository.ResidentRepository;
import pt.pedrorocha.mybuilding.services.ResidentService;

import java.util.List;

@RestController
@RequestMapping("${api.base-resident-path}")
public class ResidentController {

    ResidentService residentService;

    public ResidentController(ResidentService residentService){this.residentService = residentService;}

    @RequestMapping(method=RequestMethod.GET, path = {"/", "", "/list"})
    public ResponseEntity<List<Resident>> list(){
        try {
            return new ResponseEntity<>(residentService.list(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/add", "/add/"})
    public ResponseEntity<String> addResident(@RequestBody Resident resident){
        try {
            residentService.add(resident);
            return new ResponseEntity<>("Resident added successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
