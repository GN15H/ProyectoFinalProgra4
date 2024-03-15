package modelos;

public class User {
    private String idType;
    private String id;
    private String names;
    private String lastNames;
    private String email;
    private String residenceAddress;
    private String residenceCity;
    private String phone;
    private String password;

    public String getIdType() {
        return idType;
    }

    public String getId() {
        return id;
    }

    public String getNames() {
        return names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public String getEmail() {
        return email;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public String getResidenceCity() {
        return residenceCity;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setIdType(String idType) {
        this.idType = idType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public void setResidenceCity(String residenceCity) {
        this.residenceCity = residenceCity;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public User(String idType, String id, String names, String lastNames, String email, String residenceAddress, String residenceCity, String phone, String password) {
        this.idType = idType;
        this.id = id;
        this.names = names;
        this.lastNames = lastNames;
        this.email = email;
        this.residenceAddress = residenceAddress;
        this.residenceCity = residenceCity;
        this.phone = phone;
        this.password = password;
    }
}
