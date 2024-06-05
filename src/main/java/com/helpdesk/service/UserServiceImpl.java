package com.helpdesk.service;

import com.helpdesk.Utils.RolEnum;
import com.helpdesk.mappers.UserMapper;
import com.helpdesk.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Users getUserById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public List<Users> getUserByRol(RolEnum role) {
        return  userMapper.findItAnalyst(role);
    }

    @Override
    public List<Users> getAllUsers() {
        return userMapper.findAll();
    }

    @Override
    public void save(Users usersDto) {
        // Mapear UsuarioDTO a Users entity
        Users users = new Users();
        users.setNombre(usersDto.getNombre());
        users.setApellidos(usersDto.getApellidos());
        users.setArea(usersDto.getArea());
        users.setRol(usersDto.getRol());
        /*
        Users user = Users.builder()
                .nombre()
                .apellidos()
                .area(users.getArea())
                .rol()
                .build();

         */
        // Insertar el usuario en la base de datos
        userMapper.insert(users);
    }

    @Override
    @Transactional
    public void update(Users user, Long id) {
        userMapper.updateUser(user, id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userMapper.deleteUserById(id);
    }
}
