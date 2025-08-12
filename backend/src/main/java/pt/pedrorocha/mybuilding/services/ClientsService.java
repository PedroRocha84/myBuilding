package pt.pedrorocha.mybuilding.services;

import pt.pedrorocha.mybuilding.repository.BuildingRepository;

public class ClientsService {

    private final BuildingRepository buildingRepository;

    public ClientsService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }
}
