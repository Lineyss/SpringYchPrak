package com.praktic.Shish.DTO;

import com.praktic.Shish.Model.Help.Enum.ERole;
import jakarta.persistence.*;

import java.util.Set;

public class UserDTO {

    private String Login;
    private String Password;
    private boolean active;

    private Set<ERole> roles;

    public UserDTO() {}

    public UserDTO(String login, String password, boolean active, Set<ERole> roles) {
        Login = login;
        Password = password;
        this.active = active;
        this.roles = roles;
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

    public boolean getActive() {
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
