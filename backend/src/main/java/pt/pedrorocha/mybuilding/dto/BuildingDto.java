package pt.pedrorocha.mybuilding.dto;

public class BuildingDto {

    public Integer id;
    public String name;
    public String alias;
    public int vatNumber;
    public String description;
    public String street;
    public String postCode;
    public String city;
    public String country;
    public String district;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public int getVatNumber() {
        return vatNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getStreet() {
        return street;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getDistrict() {
        return district;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setVatNumber(int vatNumber) {
        this.vatNumber = vatNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
