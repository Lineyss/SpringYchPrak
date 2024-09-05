package com.praktic.Shish.Interface;

import jakarta.annotation.Nullable;

import java.util.ArrayList;

public interface ICrudRepository<Model, DTO> {
    ArrayList<Model> GetAll();
    @Nullable
    Model GetByID(int ID);
    boolean Create(DTO model);
    boolean Update(int ID, DTO model);
    boolean Delete(int ID);
}
