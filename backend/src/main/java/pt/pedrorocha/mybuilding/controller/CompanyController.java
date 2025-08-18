package pt.pedrorocha.mybuilding.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.pedrorocha.mybuilding.model.Company;
import pt.pedrorocha.mybuilding.services.CompanyService;

import java.util.List;

@RestController
@RequestMapping("${api.base-company-path}")
public class CompanyController {

    CompanyService companyService;
    boolean companyExists;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(method=RequestMethod.GET, path = {"/", "", "/list"})
    public ResponseEntity<List<Company>> list() {
        try {
            return new ResponseEntity<>(companyService.list(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/add", "/add/"})
    public ResponseEntity<String> addClient(@RequestBody Company company) {
        try {
            companyService.add(company);
            return new ResponseEntity<>("Company " + company.getAlias() + " was created!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/update/{id}"})
    public ResponseEntity<String> updateCompany(@RequestBody Company company, @PathVariable Long id) {
        try {
            companyExists = companyService.getCompanyByID(id);

            if (!companyExists) {
                return new ResponseEntity<>("Company "  +  company.getAlias() + " not found", HttpStatus.NOT_FOUND);
            }

            companyService.update(id, company);
            return new ResponseEntity<>("Company "  +  company.getAlias() + " updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        try {
            companyExists = companyService.getCompanyByID(id);

            if (!companyExists) {
                return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
            }

            companyService.delete(id);
            return new ResponseEntity<>("Company deleted.", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
