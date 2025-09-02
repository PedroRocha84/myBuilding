package pt.pedrorocha.mybuilding.dto;

public class ResidentResponseDto {
    private String firstName;
    private String lastName;
    private Long clientGroupId;
    private Long buildingId;
    private String fraction;

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Long getClientGroupId() { return clientGroupId; }
    public Long getBuildingId() { return buildingId; }
    public String getFraction() { return fraction; }

    // Setters
    
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setClientGroupId(Long clientGroupId) { this.clientGroupId = clientGroupId; }
    public void setBuildingId(Long buildingId) { this.buildingId = buildingId; }
    public void setFraction(String fraction) { this.fraction = fraction; }
}

