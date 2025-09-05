package pt.pedrorocha.mybuilding.services;

import org.springframework.stereotype.Service;
import pt.pedrorocha.mybuilding.entity.ClientGroup;
import pt.pedrorocha.mybuilding.repository.ClientGroupRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class ClientGroupService {

    private ClientGroupRepository clientGroupRepository;

    public ClientGroupService(ClientGroupRepository clientGroupRepository) {this.clientGroupRepository = clientGroupRepository;}

    public List<ClientGroup> list() {
        return new ArrayList<>(clientGroupRepository.findAll());
    }

    @Transactional
    public void add(ClientGroup clientGroup) {
       String name = clientGroup.getName();

       if (clientGroupRepository.existsByName(name)) {
           throw new IllegalArgumentException("Name already exists");
       }
        clientGroupRepository.save(clientGroup);
    }

    @Transactional
    public void update(long id, ClientGroup clientGroup) {
        clientGroupRepository.findById(id)
                .map(exist -> {
                    exist.setName(clientGroup.getName());

                    return clientGroupRepository.save(exist);
                })
                .orElseThrow(() -> new RuntimeException("Client Group not found!"));
    }

    @Transactional
    public void delete(long idClientGroup) {
        ClientGroup clientGroup =  clientGroupRepository.findById(idClientGroup)
                .orElseThrow(() -> new RuntimeException("Client Group not found!"));
        clientGroupRepository.delete(clientGroup);
    }

    public ClientGroup findById(long idClientGroup) {
        return clientGroupRepository.findById(idClientGroup)
                .orElseThrow(() -> new RuntimeException("Client Group not found!"));
    }
}
