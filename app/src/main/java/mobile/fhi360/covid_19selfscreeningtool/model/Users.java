package mobile.fhi360.covid_19selfscreeningtool.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Users implements Serializable {
    private Long id;

    @SerializedName("firstname")
    @Expose
    private String firstname;

    @SerializedName("lastname")
    @Expose
    private String lastname;

    @SerializedName("designation")
    @Expose
    private String designation;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("userType")
    @Expose
    private String userType;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("state")
    @Expose
    private String state;

    private String enabled;

    @SerializedName("supervisedBy")
    @Expose
    private String supervisedBy;


    public Users(Long id, String firstname, String lastname, String email, String password, String userType,
                 String gender, String state, String designation, String enabled, String supervisedBy) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.gender = gender;
        this.state = state;
        this.designation = designation;
        this.enabled = enabled;
        this.supervisedBy = supervisedBy;
    }

    public String getEnabled() {
        return enabled;
    }

    public Long getSupervisorId() {
        return getId() ;   }

    public void setSupervisorId(Long id) {
        this.id = getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String isEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getSupervisedBy() {
        return supervisedBy;
    }

    public void setSupervisedBy(String supervisedBy) {
        this.supervisedBy = supervisedBy;
    }
}
