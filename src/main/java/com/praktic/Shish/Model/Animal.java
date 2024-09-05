package com.praktic.Shish.Model;

public class Animal {
    private int ID;
    private String Name;
    private String Type;
    private boolean IsDeleted = false;

    public Animal(int ID, String Name, String Type, boolean IsDeleted)
    {
        this.ID = ID;
        this.Name = Name;
        this.Type = Type;
        this.IsDeleted = IsDeleted;
    }

    public Animal(int ID, String Name, String Type)
    {
        this.ID = ID;
        this.Name = Name;
        this.Type = Type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public boolean isDeleted() {
        return IsDeleted;
    }

    public void setDeleted(boolean deleted)
    {
        IsDeleted = deleted;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
