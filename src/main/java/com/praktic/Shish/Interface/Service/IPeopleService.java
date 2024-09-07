package com.praktic.Shish.Interface.Service;

import com.praktic.Shish.Model.Help.Pagination;
import com.praktic.Shish.Model.People;
import jakarta.annotation.Nullable;

import java.util.HashSet;

public interface IPeopleService {

    Pagination<People> GetAll(int page, @Nullable String firstName, @Nullable String lastName, @Nullable Boolean IsDeleted);
    HashSet<String> GetAllCategory();
}
