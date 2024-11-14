package com.cesur.splinterio.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesur.splinterio.models.User;
import com.cesur.splinterio.models.dtos.UserDTO;
import com.cesur.splinterio.models.utils.mappers.UserMapper;
import com.cesur.splinterio.repositories.UserRepository;
import com.cesur.splinterio.services.UserService;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.getUserByEmail(email).get();
        return UserMapper.instance.userToUserDTOWithoutPassword(user);
    }

    @Override
    public void updateUser(UserDTO user) {
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public void deleteUser(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    @Override
    public void storeUser(UserDTO userFromControl) {
        User userToDb = UserMapper.instance.userDTOToUserDB(userFromControl);
        userRepository.save(userToDb);
    }

}
