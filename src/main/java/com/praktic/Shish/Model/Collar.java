package com.praktic.Shish.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "collar")
public class Collar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String Title;

    @ManyToMany
    @JoinTable(
            name = "animal_collar",
            joinColumns = @JoinColumn(name = "animal_id"),
            inverseJoinColumns = @JoinColumn(name = "collar_id")
    )
    private List<Animal> animal;

    public Collar()
    {

    }

    public Collar(Long id, String title) {
        ID = id;
        Title = title;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
