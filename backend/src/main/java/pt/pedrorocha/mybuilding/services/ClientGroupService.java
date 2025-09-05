package pt.pedrorocha.mybuilding.services;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import pt.pedrorocha.mybuilding.entity.ClientGroup;
import pt.pedrorocha.mybuilding.repository.ClientGroupRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ClientGroupService {

    private final ClientGroupRepository clientGroupRepository;

    public ClientGroupService(ClientGroupRepository clientGroupRepository) {this.clientGroupRepository = clientGroupRepository;}

    public List<ClientGroup> list() {
       return clientGroupRepository.findAll();
    }

    @Transactional
    public ClientGroup add(ClientGroup clientGroup) {
       if (clientGroupRepository.existsByName(clientGroup.getName())) {
           throw new EntityExistsException("Client group with name " + clientGroup.getName() + " already exists");
       }
       return clientGroupRepository.save(clientGroup);

    }

    @Transactional
    public ClientGroup update(long idClientGroup, ClientGroup clientGroup) {
        ClientGroup toSave = clientGroupRepository.getOne(idClientGroup);
        toSave.setName(clientGroup.getName());

        return clientGroupRepository.save(toSave);
    }

    @Transactional
    public void delete(long idClientGroup) {
        if(!clientGroupRepository.existsById(idClientGroup)) {
            throw new EntityNotFoundException("Client group with id " + idClientGroup + "does not exist");
        }
        clientGroupRepository.delete(clientGroupRepository.getReferenceById(idClientGroup));
    }

    public ClientGroup findById(long idClientGroup) {
        return clientGroupRepository.findById(idClientGroup)
                .orElseThrow(() -> new EntityNotFoundException("Client Group with id " + idClientGroup + " not found"));
    }

}
