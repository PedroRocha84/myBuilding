package pt.pedrorocha.mybuilding.services;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.pedrorocha.mybuilding.entity.Company;
import pt.pedrorocha.mybuilding.repository.CompanyRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository clientsRepository){this.companyRepository = clientsRepository;}

    public List<Company> list(){
        return new ArrayList<>(companyRepository.findAll());
    }

    @Transactional
    public void add(Company company){
        String name = company.getAlias();
        Integer vatNumber = company.getVatNumber();

        if(companyRepository.existsByAlias(name) ||  companyRepository.existsByvatNumber(vatNumber)){
            throw new EntityExistsException("Company with name " + name + " already exists!");
        }
        companyRepository.save(company);
    }

    @Transactional
    public void update(Long id, Company company){
        companyRepository.findById(id)
                .map(exist -> {
                    exist.setAlias(company.getAlias());
                    exist.setCity(company.getCity());
                    exist.setDistrict(company.getDistrict());
                    exist.setEmail(company.getEmail());
                    exist.setPersonInCharge(company.getPersonInCharge());
                    exist.setPhoneNumber(company.getPhoneNumber());
                    exist.setPostalCode(company.getPostalCode());
                    exist.setStreet(company.getStreet());
                    exist.setVatNumber(company.getVatNumber());
                    return companyRepository.save(exist);
                })
                .orElseThrow(() -> new RuntimeException("Client not found!"));
    }

    @Transactional
    public void delete(Long companyId){
        if(!companyRepository.existsById(companyId)){
            throw new EntityNotFoundException("Company with id " + companyId + " does not exist");
        }
        companyRepository.deleteById(companyId);
    }
}
