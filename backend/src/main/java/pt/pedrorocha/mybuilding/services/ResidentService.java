package pt.pedrorocha.mybuilding.services;

import org.springframework.stereotype.Service;
import pt.pedrorocha.mybuilding.repository.ResidentRepository;

@Service
public class ResidentService {

private ResidentRepository residentRepository;

public ResidentRepository getResidentRepository() {return residentRepository;}

}
