package com.helpdesk.service;

import com.helpdesk.Utils.RolEnum;
import com.helpdesk.model.Users;

import java.util.List;

public interface UserService {
    public Users getUserById(Long id);
    public List<Users> getUserByRol(RolEnum role);
    public List<Users> getAllUsers();
    public void save(Users users);

    public void update(Users user, Long id);
    public void delete(Long id);
}
