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

    public List<Client> loadClients(){
        return new ArrayList<>(clientRepository.findAll());
    }

    @Transactional
    public Client addClient(Client client){
        clientRepository.save(client);
        return client;
    }
}
