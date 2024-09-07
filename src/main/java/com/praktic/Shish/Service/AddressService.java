package com.praktic.Shish.Service;

import com.praktic.Shish.DTO.AddressDTO;
import com.praktic.Shish.Interface.AService;
import com.praktic.Shish.Interface.Repository.IAddressRepository;
import com.praktic.Shish.Model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class AddressService extends AService<Address, AddressDTO> {

    @Autowired
    public AddressService(IAddressRepository repository) {
        super(repository);
    }

    @Override
    public boolean Delete(Long ID) {
        return false;
    }

    @Override
    protected Address ConvertDTOtoEntity(AddressDTO addressDTO) {
        return null;
    }

    @Override
    protected Address UpdateDTOtoEntity(Address address, AddressDTO addressDTO) {
        return null;
    }

    @Override
    protected Class<Address> getEntityClass() {
        return null;
    }
}
