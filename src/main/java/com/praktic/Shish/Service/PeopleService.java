package com.praktic.Shish.Service;

import com.praktic.Shish.DTO.PeopleDTO;
import com.praktic.Shish.Interface.AService;
import com.praktic.Shish.Interface.IPeopleRepository;
import com.praktic.Shish.Interface.IPeopleService;
import com.praktic.Shish.Model.Animal;
import com.praktic.Shish.Model.Pagination;
import com.praktic.Shish.Model.People;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class PeopleService
        extends AService<People, PeopleDTO>
        implements IPeopleService {

    @Autowired
    public PeopleService(IPeopleRepository repository) {
        super(repository);
    }

    @Override
    public Pagination<People> GetAll(int page, @Nullable String firstName, @Nullable String lastName, @Nullable Boolean IsDeleted) {
        ArrayList<People> peoples = new ArrayList<>(repository.findAll());

        if(IsDeleted != null) {
            peoples = peoples.stream()
                    .filter(animal -> animal.isDeleted() == IsDeleted)
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        if (firstName != null) {
            peoples = peoples.stream()
                    .filter(_people -> _people.getFirstName().equalsIgnoreCase(firstName))
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        if (lastName != null) {
            peoples = peoples.stream()
                    .filter(_people -> _people.getLastName().equalsIgnoreCase(lastName))
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        return new Pagination<People>(peoples, page);
    }


    @Override
    public boolean Delete(Long ID) {
        People findPepople = GetByID(ID);
        if(findPepople != null)
        {
            if(findPepople.isDeleted())
            {
                repository.deleteById(ID);
            }
            else
            {
                findPepople.setDeleted(true);
                repository.save(findPepople);
            }
        }
        return true;
    }

    @Override
    protected People ConvertDTOtoEntity(PeopleDTO peopleDTO) {
        return new People(peopleDTO.getFirstName(), peopleDTO.getMiddleName(), peopleDTO.getLastName());
    }

    @Override
    protected People UpdateDTOtoEntity(People people, PeopleDTO peopleDTO) {
        people.setMiddleName(peopleDTO.getMiddleName());
        people.setLastName(peopleDTO.getLastName());
        people.setFirstName(peopleDTO.getFirstName());

        return people;
    }

    @Override
    protected Class<People> getEntityClass() {
        return People.class;
    }

    @Override
    public HashSet<String> GetAllCategory() {
        HashSet<String> hashSet = new HashSet<>();

        for(People people : repository.findAll())
        {
            hashSet.add(people.getLastName());
        }

        return hashSet;
    }
}
