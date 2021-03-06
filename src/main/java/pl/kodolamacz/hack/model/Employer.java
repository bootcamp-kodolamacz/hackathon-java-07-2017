package pl.kodolamacz.hack.model;

import org.hibernate.annotations.*;
import javax.persistence.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "employer")
public class Employer extends AbstractEntity {

    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    @NotEmpty
    private String location;
    @Column(name = "employees_count")
    @NotNull
    private int employeesCount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Employer() {
    }

    public Employer(String name, String email, String location, int employeesCount, User user) {
        this.name = name;
        this.email = email;
        this.location = location;
        this.employeesCount = employeesCount;
        this.user = user;
    }

    public Employer(String name, String email, String location, int employeesCount) {
        this.name = name;
        this.email = email;
        this.location = location;
        this.employeesCount = employeesCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(int employeesCount) {
        this.employeesCount = employeesCount;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
