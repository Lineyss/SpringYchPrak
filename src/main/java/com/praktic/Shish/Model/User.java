package com.praktic.Shish.Model;

import com.praktic.Shish.Model.Help.Enum.ERole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
@Table(name= "usr")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String Login;

    @Size(min = 3, message = "Минимальная длинна пароля 3 символа")
    private String Password;
    private boolean active;

    @ElementCollection(targetClass = ERole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<ERole> roles;

    public User() {}

    public User(Long id, String login, String password, boolean active, Set<ERole> roles) {
        ID = id;
        Login = login;
        Password = password;
        this.active = active;
        this.roles = roles;
    }

    public User(String login, String password, boolean active, Set<ERole> roles) {
        Login = login;
        Password = password;
        this.active = active;
        this.roles = roles;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<ERole> getRoles() {
        return roles;
    }

    public void setRoles(Set<ERole> roles) {
        this.roles = roles;
    }
}
