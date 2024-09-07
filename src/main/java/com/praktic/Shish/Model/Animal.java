package com.praktic.Shish.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов")
    private String Name;
    @Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов")
    private String Type;

    @ManyToMany(mappedBy = "animal")
    private List<Collar> collar;

    @Column(columnDefinition = "boolean default false")
    private boolean IsDeleted;

    @OneToMany(mappedBy = "animal")
    private List<People> people;

    public Animal()
    {

    }

    public Animal(@Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов") String Name,
                  @Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов") String Type)
    {
        this.Name = Name;
        this.Type = Type;
    }

    public Animal(Long ID,
                  @Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов") String Name,
                  @Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов") String Type)
    {
        this.ID = ID;
        this.Name = Name;
        this.Type = Type;
    }

    public @Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов") String getName() {
        return Name;
    }

    public void setName(@Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов") String name) {
        Name = name;
    }

    public @Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов") String getType() {
        return Type;
    }

    public void setType(@Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов") String type) {
        Type = type;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public boolean isDeleted() {
        return IsDeleted;
    }

    public void setDeleted(boolean deleted) {
        IsDeleted = deleted;
    }

    public List<People> getPeople() {
        return people;
    }

    public void setPeople(List<People> people) {
        this.people = people;
    }
}
