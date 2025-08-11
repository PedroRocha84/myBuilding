package pt.pedrorocha.mybuilding.services;

import pt.pedrorocha.mybuilding.model.Building;

import java.util.List;

public interface BuildingService {
    void add(Building building);

    void remove(Building building);

    void update(Building building);

    List<Building> getAll();
}
