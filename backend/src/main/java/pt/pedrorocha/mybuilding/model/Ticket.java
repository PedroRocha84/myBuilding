package pt.pedrorocha.mybuilding.model;

import org.springframework.stereotype.Service;

@Service
public class Ticket extends AbstractModel {
    private String title;
    private String description;
    private String status;
    private Resident resident;
    private Building building;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public Resident getResident() {
        return resident;
    }

    public Building getBuilding() {
        return building;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
