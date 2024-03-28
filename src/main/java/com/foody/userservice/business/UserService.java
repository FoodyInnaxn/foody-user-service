package com.foody.userservice.business;

import com.foody.userservice.dto.CreateUserResponse;
import com.foody.userservice.dto.UserRequest;
import com.foody.userservice.dto.UserResponse;

public interface UserService {
    CreateUserResponse createUser(UserRequest request);
    UserResponse getUserById(Long id);
}
