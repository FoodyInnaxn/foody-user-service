package com.foody.userservice.business;

import com.foody.userservice.dto.*;

public interface UserService {
    void createUser(UserToReceive request);
    UserInfo getUserById(Long id);
    void deleteUser(Long id);
    void updateUser(Long id, UpdateBasicUserRequest request);
}
