package pt.pedrorocha.mybuilding.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="buildings")
public class Building extends AbstractModel  {

    public String name;
    public String alias;
    public int vatNumber;
    public String description;
    public String street;
    public String postCode;
    public String city;
    public String country;
    public String district;

    @ManyToOne(optional = true) // allow null
    @JoinColumn(name = "company_id", nullable = true)
    private Company company;

    @OneToOne(mappedBy = "building", cascade = CascadeType.ALL)
    private ClientGroup clientGroup;

    @OneToMany(mappedBy = "building") private List<Resident> residents = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(int vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
