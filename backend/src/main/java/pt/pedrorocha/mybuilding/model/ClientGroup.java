package pt.pedrorocha.mybuilding.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ClientGroup extends AbstractModel{

    private String groupName;

    private void setGroupName(String groupName) {this.groupName = groupName;}

    public void setBuilding(Building building) {this.building = building;}

    public String getGroupName() {return groupName;}

    public Building getBuilding() {return building;}

    @OneToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @OneToMany(mappedBy = "clientGroup", cascade = CascadeType.ALL)
    private List<Resident> residents;

}
