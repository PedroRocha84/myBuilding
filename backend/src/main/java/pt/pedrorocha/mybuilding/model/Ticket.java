package pt.pedrorocha.mybuilding.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tickets")
public class Ticket extends AbstractModel {
    private String title;
    private String description;
    private String status;
    @ManyToOne
    @JoinColumn(name = "resident_id")
    private Resident resident;
    @ManyToOne
    @JoinColumn(name = "building_id")
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
