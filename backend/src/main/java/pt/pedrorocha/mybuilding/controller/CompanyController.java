package pt.pedrorocha.mybuilding.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.pedrorocha.mybuilding.entity.Company;
import pt.pedrorocha.mybuilding.services.CompanyService;

import java.util.List;

@RestController
@RequestMapping("${api.base-company-path}")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return new ResponseEntity<>(companyService.list(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createCompanies(@RequestBody Company company) {
            companyService.add(company);
            return new ResponseEntity<>("Company " + company.getAlias() + " was created!", HttpStatus.CREATED);
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<String> updateCompany(@RequestBody Company company, @PathVariable Long companyId) {
        companyService.update(companyId, company);
        return new ResponseEntity<>("Company " + company.getAlias() + " was updated!", HttpStatus.OK);

    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long companyId) {
        companyService.delete(companyId);
        return new ResponseEntity<>("Company " + companyId + " was deleted!", HttpStatus.NO_CONTENT);
    }
}
