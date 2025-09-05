package pt.pedrorocha.mybuilding.entity;

import jakarta.persistence.*;
import pt.pedrorocha.mybuilding.model.AbstractModel;

@Entity
@Table(name="residents")
public class Resident extends AbstractModel {

    private String firstName;
    private String lastName;
    private String fraction; // Optional, for residents


    @ManyToOne
    @JoinColumn(name = "client_group_id", nullable = true)
    private ClientGroup clientGroup;

    @ManyToOne
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFraction() {
        return fraction;
    }

    public void setFraction(String fraction) {
        this.fraction = fraction;
    }

    public ClientGroup getClientGroup() {
        return clientGroup;
    }

    public void setClientGroup(ClientGroup clientGroup) {
        this.clientGroup = clientGroup;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
