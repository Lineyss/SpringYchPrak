package com.praktic.Shish.DTO;

public class AnimalDTO {
    private String Name;
    private String Type;
    public AnimalDTO(String name, String Type)
    {
        Name = name;
        this.Type = Type;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
