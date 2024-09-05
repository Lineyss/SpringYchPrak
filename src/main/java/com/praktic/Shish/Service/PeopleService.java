package com.praktic.Shish.Service;

import com.praktic.Shish.DTO.PeopleDTO;
import com.praktic.Shish.Interface.ICrudRepository;
import com.praktic.Shish.Interface.IPeopleService;
import com.praktic.Shish.Model.Animal;
import com.praktic.Shish.Model.Pagination;
import com.praktic.Shish.Model.People;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class PeopleService implements IPeopleService {

    @Autowired
    private ICrudRepository<People, PeopleDTO> peopleRepository;

    @Override
    public Pagination<People> GetAll(int page, @Nullable String firstName, @Nullable String lastName) {
        ArrayList<People> people = peopleRepository.GetAll()
                .stream()
                .filter(element -> !element.isDeleted())
                .collect(Collectors.toCollection(ArrayList::new));

        if (firstName != null) {
            people = people.stream()
                    .filter(_people -> _people.getFirstName().equalsIgnoreCase(firstName))
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        if (lastName != null) {
            people = people.stream()
                    .filter(_people -> _people.getLastName().equalsIgnoreCase(lastName))
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        return new Pagination<People>(people, page);
    }

    @Nullable
    @Override
    public People GetByID(int ID) {
        return peopleRepository.GetByID(ID);
    }

    @Override
    public boolean Create(PeopleDTO peopleDTO) {
        return peopleRepository.Create(peopleDTO);
    }

    @Override
    public boolean Update(int ID, PeopleDTO peopleDTO) {
        return peopleRepository.Update(ID, peopleDTO);
    }

    @Override
    public boolean Delete(int ID) {
        return peopleRepository.Delete(ID);
    }

    @Override
    public HashSet<String> GetAllCategory() {
        HashSet<String> hashSet = new HashSet<>();

        for(People people : peopleRepository.GetAll())
        {
            hashSet.add(people.getLastName());
        }

        return hashSet;
    }
}
