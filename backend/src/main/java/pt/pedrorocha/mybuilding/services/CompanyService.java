package pt.pedrorocha.mybuilding.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.pedrorocha.mybuilding.model.Company;
import pt.pedrorocha.mybuilding.repository.CompanyRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository clientRepository;

    public CompanyService(CompanyRepository clientsRepository){this.clientRepository = clientsRepository;}

    public boolean getCompanyByID(long id){
        return clientRepository.findById(id).isPresent();
    }

    public List<Company> list(){
        return new ArrayList<>(clientRepository.findAll());
    }

    @Transactional
    public Company add(Company company){
        clientRepository.save(company);
        return company;
    }

    @Transactional
    public void update(Long id, Company company){
        clientRepository.findById(id)
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

                    return clientRepository.save(exist);
                })
                .orElseThrow(() -> new RuntimeException("Client not found!"));
    }

    @Transactional
    public String delete(Long id){
        if(clientRepository.findById(id).isPresent()){
            clientRepository.deleteById(id);
            return "Client deleted!";
        }
        return "Client not found!";
    }
}
