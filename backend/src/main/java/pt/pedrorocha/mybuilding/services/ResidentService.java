package pt.pedrorocha.mybuilding.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.pedrorocha.mybuilding.dto.ResidentDto;
import pt.pedrorocha.mybuilding.mapper.ResidentMapper;
import pt.pedrorocha.mybuilding.model.Building;
import pt.pedrorocha.mybuilding.model.ClientGroup;
import pt.pedrorocha.mybuilding.model.Resident;
import pt.pedrorocha.mybuilding.repository.ResidentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResidentService {

@Autowired
private ResidentRepository residentRepository;

@Autowired
private ClientGroupService clientGroupService;

@Autowired
private ResidentMapper residentMapper;


    public List<Resident> list(){
        return new ArrayList<>(residentRepository.findAll());
    }

    @Transactional
    public ResidentDto add(ResidentDto dto, Building building) {
        Resident resident = new Resident();
        resident.setFirstName(dto.getFirstName());
        resident.setLastName(dto.getLastName());
        resident.setFraction(dto.getFraction());
        resident.setBuilding(building);

        if (dto.getClientGroupId() != null) {
            ClientGroup clientGroup = clientGroupService.findById(dto.getClientGroupId());
            resident.setClientGroup(clientGroup);
        }

        Resident savedResident = residentRepository.save(resident);
        return residentMapper.ToDto(savedResident);
    }


    @Transactional
    public ResidentDto update(Long id, ResidentDto dto) {

        // Check if resident exists
        Resident existingResident = residentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Resident not found with id: " + id));

        // Update all fields (full update)
        existingResident.setFirstName(dto.getFirstName());
        existingResident.setLastName(dto.getLastName());
        existingResident.setFraction(dto.getFraction());

        // Handle client group update
        if (dto.getClientGroupId() != null) {
            ClientGroup clientGroup = clientGroupService.findById(dto.getClientGroupId());
            existingResident.setClientGroup(clientGroup);
        } else {
            existingResident.setClientGroup(null);
        }

        Resident savedResident = residentRepository.save(existingResident);

        return residentMapper.ToDto(savedResident);
    }

    public Resident findById(Long id) {
        return residentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Resident not found with id: " + id));
    }

}
