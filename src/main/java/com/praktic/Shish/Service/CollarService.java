package com.praktic.Shish.Service;

import com.praktic.Shish.DTO.CollarDTO;
import com.praktic.Shish.Interface.AService;
import com.praktic.Shish.Interface.Repository.ICollarRepository;
import com.praktic.Shish.Model.Collar;
import org.springframework.beans.factory.annotation.Autowired;

public class CollarService extends AService<Collar, CollarDTO> {

    @Autowired
    public CollarService(ICollarRepository repository) {
        super(repository);
    }

    @Override
    public boolean Delete(Long ID) {
        return false;
    }

    @Override
    protected Collar ConvertDTOtoEntity(CollarDTO collarDTO) {
        return null;
    }

    @Override
    protected Collar UpdateDTOtoEntity(Collar collar, CollarDTO collarDTO) {
        return null;
    }

    @Override
    protected Class<Collar> getEntityClass() {
        return null;
    }
}
