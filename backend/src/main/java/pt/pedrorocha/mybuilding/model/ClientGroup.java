package pt.pedrorocha.mybuilding.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ClientGroup extends AbstractModel{

    private String groupName;

    @OneToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @OneToMany(mappedBy = "clientGroup", cascade = CascadeType.ALL)
    private List<Resident> residents;

}
