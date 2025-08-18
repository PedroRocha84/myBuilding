package pt.pedrorocha.mybuilding.services;

import org.springframework.stereotype.Service;
import pt.pedrorocha.mybuilding.model.Resident;
import pt.pedrorocha.mybuilding.repository.ResidentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResidentService {

private ResidentRepository residentRepository;
private ResidentService residentService;

public ResidentService(ResidentRepository residentRepository) {this.residentRepository = residentRepository;}

    public List<Resident> list(){
        return new ArrayList<>(residentRepository.findAll());
    }

    public Resident add(Resident resident){
        residentRepository.save(resident);
        return resident;
    }

}
