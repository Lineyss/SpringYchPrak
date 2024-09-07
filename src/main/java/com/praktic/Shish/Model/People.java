package com.praktic.Shish.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="people")
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов")
    private String FirstName;
    @Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов")
    private String MiddleName;
    @Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов")
    private String LastName;
    @Column(columnDefinition = "boolean default false")
    private boolean IsDeleted;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public People()
    {

    }

    public People(@Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов") String firstName,
                  @Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов") String middleName,
                  @Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов") String lastName) {
        FirstName = firstName;
        MiddleName = middleName;
        LastName = lastName;
    }

    public People(Long id,
                  @Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов") String firstName,
                  @Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов") String middleName,
                  @Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов") String lastName) {
        ID = id;
        FirstName = firstName;
        MiddleName = middleName;
        LastName = lastName;
    }

    public @Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов") String getFirstName() {
        return FirstName;
    }

    public void setFirstName(@Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов") String firstName) {
        FirstName = firstName;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public @Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов") String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(@Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов") String middleName) {
        MiddleName = middleName;
    }

    public @Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов") String getLastName() {
        return LastName;
    }

    public void setLastName(@Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов") String lastName) {
        LastName = lastName;
    }

    public boolean isDeleted() {
        return IsDeleted;
    }

    public void setDeleted(boolean deleted) {
        IsDeleted = deleted;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
