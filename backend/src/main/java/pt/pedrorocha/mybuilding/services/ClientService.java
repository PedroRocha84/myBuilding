package pt.pedrorocha.mybuilding.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.pedrorocha.mybuilding.model.Client;
import pt.pedrorocha.mybuilding.repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientsRepository){this.clientRepository = clientsRepository;}

    public boolean getClientByID(long id){
        return clientRepository.findById(id).isPresent();
    }

    public List<Client> list(){
        return new ArrayList<>(clientRepository.findAll());
    }

    @Transactional
    public Client add(Client client){
        clientRepository.save(client);
        return client;
    }

    @Transactional
    public void update(Long id, Client client){
        clientRepository.findById(id)
                .map(exist -> {
                    exist.setAlias(client.getAlias());
                    exist.setCity(client.getCity());
                    exist.setDistrict(client.getDistrict());
                    exist.setEmail(client.getEmail());
                    exist.setPersonInCharge(client.getPersonInCharge());
                    exist.setPhoneNumber(client.getPhoneNumber());
                    exist.setPostalCode(client.getPostalCode());
                    exist.setStreet(client.getStreet());
                    exist.setVatNumber(client.getVatNumber());

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
