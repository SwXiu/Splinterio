package com.cesur.splinterio.services;

import com.cesur.splinterio.models.dtos.UserDTO;

public interface UserService {
    UserDTO getUserByEmail(String email);
    void updateUser(UserDTO user);
    void deleteUser(Long id);
    void storeUser(UserDTO user);
}
