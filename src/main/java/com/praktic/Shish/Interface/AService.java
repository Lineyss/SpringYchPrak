package com.praktic.Shish.Interface;

import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public abstract class AService<Entity, ModelDTO> {

    protected final JpaRepository<Entity, Long> repository;

    public AService(JpaRepository<Entity, Long> repository)
    {
        this.repository = repository;
    }

    @Nullable
    public Entity GetByID(Long ID)
    {
        return repository.findById(ID).orElse(null);
    }

    public boolean Create(ModelDTO modelDTO) {
        Entity entity = ConvertDTOtoEntity(modelDTO);
        repository.save(entity);
        return true;
    }

    public boolean Update(Long ID, ModelDTO modelDTO) {
        Entity findAnimal = GetByID(ID);
        if(findAnimal != null)
        {
            findAnimal = UpdateDTOtoEntity(findAnimal, modelDTO);
            repository.save(findAnimal);
            return true;
        }

        return false;
    }

    public String[] GetAllFieldsForSearch()
    {
        Field[] _fields = getEntityClass().getDeclaredFields();
        String[] fields = new String[_fields.length];
        for(int i = 0; i< fields.length;i++)
        {
            String field = _fields[i].getName();
            fields[i] = field;
        }

        return fields;
    }


    public abstract boolean Delete(Long ID);

    protected abstract Entity ConvertDTOtoEntity(ModelDTO modelDTO);

    protected abstract Entity UpdateDTOtoEntity(Entity entity, ModelDTO modelDTO);

    protected abstract Class<Entity> getEntityClass();
}
