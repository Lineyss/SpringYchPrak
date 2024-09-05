package com.praktic.Shish.Repository;

import com.praktic.Shish.DTO.PeopleDTO;
import com.praktic.Shish.Interface.ICrudRepository;
import com.praktic.Shish.Model.People;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PeolpeRepository implements ICrudRepository<People, PeopleDTO> {

    private AtomicInteger idCounter = new AtomicInteger(1);
    private final ArrayList<People> peoples = new ArrayList<People>();

    @Override
    public ArrayList<People> GetAll() {
        return peoples;
    }

    @Nullable
    @Override
    public People GetByID(int ID) {
        return peoples.stream()
                .filter(people -> people.getID() == ID)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean Create(PeopleDTO model) {
        People _people = peoples.stream()
                .filter(people -> people.getFirstName().equals(model.getFirstName()) &&
                        (Objects.equals(people.getLastName(), model.getLastName())) &&
                        (Objects.equals(people.getMiddleName(), model.getMiddleName())))
                .findFirst()
                .orElse(null);

        if(_people == null)
        {
            int ID = idCounter.getAndIncrement();
            People people = new People(ID,
                                model.getFirstName(),
                                model.getMiddleName(),
                                model.getLastName());

            peoples.add(people);
            return true;
        }

        return false;
    }

    @Override
    public boolean Update(int ID, PeopleDTO model) {
        for(int i = 0;i<peoples.size();i++)
        {
            People people = peoples.get(i);
            if(people.getID() == ID)
            {
                people.setFirstName(model.getFirstName());
                people.setLastName(model.getLastName());
                people.setMiddleName(model.getMiddleName());

                peoples.set(i, people);

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean Delete(int ID) {
        People _people = GetByID(ID);

        if(_people != null)
        {
            return peoples.removeIf(people -> people.getID() == ID);
        }

        return false;
    }
}
