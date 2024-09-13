package com.praktic.Shish.Service;

import com.praktic.Shish.DTO.UserDTO;
import com.praktic.Shish.Interface.AService;
import com.praktic.Shish.Interface.Repository.IUserRepository;
import com.praktic.Shish.Interface.Service.IUserService;
import com.praktic.Shish.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AService<User, UserDTO>
                         implements IUserService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(IUserRepository repository, PasswordEncoder passwordEncoder) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean Delete(Long ID) {
        return false;
    }

    @Override
    protected User ConvertDTOtoEntity(UserDTO userDTO) {
        return new User(userDTO.getLogin(), userDTO.getPassword(), userDTO.getActive(), userDTO.getRoles());
    }

    @Override
    protected User UpdateDTOtoEntity(User user, UserDTO userDTO) {
        user.setActive(userDTO.getActive());
        user.setLogin(userDTO.getLogin());
        String password = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(password);
        user.setRoles(userDTO.getRoles());
        return user;
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
