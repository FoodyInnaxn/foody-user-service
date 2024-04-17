package com.foody.userservice.business;

import com.foody.userservice.dto.*;

import java.util.List;

public interface UserService {
    CreateUserResponse createUser(UserRequest request);
    UserResponse getUserById(Long id);
    void deleteUser(Long id);
    void updateUser(Long id, UpdateBasicUserRequest request);
}
