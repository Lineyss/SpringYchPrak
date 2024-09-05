package com.praktic.Shish.Interface;

import com.praktic.Shish.DTO.PeopleDTO;
import com.praktic.Shish.Model.Pagination;
import com.praktic.Shish.Model.People;
import jakarta.annotation.Nullable;

import java.util.HashSet;

public interface IPeopleService {

    Pagination<People> GetAll(int page, @Nullable String firstName, @Nullable String lastName);
    @Nullable
    People GetByID(int ID);
    boolean Create(PeopleDTO peopleDTO);
    boolean Update(int ID, PeopleDTO peopleDTO);
    boolean Delete(int ID);
    HashSet<String> GetAllCategory();
}
