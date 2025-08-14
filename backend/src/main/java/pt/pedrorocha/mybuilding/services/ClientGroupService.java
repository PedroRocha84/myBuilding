package pt.pedrorocha.mybuilding.services;

import org.springframework.stereotype.Service;
import pt.pedrorocha.mybuilding.model.ClientGroup;
import pt.pedrorocha.mybuilding.repository.ClientGroupRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientGroupService {

    private ClientGroupRepository clientGroupRepository;

    public ClientGroupService(ClientGroupRepository clientGroupRepository) {this.clientGroupRepository = clientGroupRepository;}

    public List<ClientGroup> list() {
        return new ArrayList<>(clientGroupRepository.findAll());
    }

    public Optional<ClientGroup> getClientGroupById(long id) {return clientGroupRepository.findById(id);}

    public String addClientGroup(ClientGroup clientGroup) {
        if(clientGroupRepository.existsByName(clientGroup.getGroupName())) {
            return "This client already exists!";
        }
        clientGroupRepository.save(clientGroup);
        return "Client group added successfully!";
    }


}
