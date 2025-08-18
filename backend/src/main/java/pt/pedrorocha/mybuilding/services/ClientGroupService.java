package pt.pedrorocha.mybuilding.services;

import org.springframework.stereotype.Service;
import pt.pedrorocha.mybuilding.model.ClientGroup;
import pt.pedrorocha.mybuilding.repository.ClientGroupRepository;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public void addClientGroup(ClientGroup clientGroup) {
        if(!clientGroupRepository.existsByName(clientGroup.getName())) {
            clientGroupRepository.save(clientGroup);
        }
    }

    @Transactional
    public void updateClientGroup(ClientGroup clientGroup) {
        if(clientGroupRepository.existsByName(clientGroup.getName())) {
            ClientGroup existing = new ClientGroup();
            existing.setName(clientGroup.getName());

            clientGroupRepository.save(existing);
        }
    }
}
