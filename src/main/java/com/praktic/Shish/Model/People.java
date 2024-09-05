package com.praktic.Shish.Model;

public class People {
    private int ID;
    private String FirstName;
    private String MiddleName;
    private String LastName;
    private boolean IsDeleted = false;

    public People(int ID, String FirstName, String MiddleName, String LastName, boolean IsDeleted)
    {
        this.ID = ID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.MiddleName = MiddleName;
        this.IsDeleted = IsDeleted;
    }

    public People(int ID, String FirstName, String MiddleName, String LastName)
    {
        this.ID = ID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.MiddleName = MiddleName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public boolean isDeleted() {
        return IsDeleted;
    }
}
