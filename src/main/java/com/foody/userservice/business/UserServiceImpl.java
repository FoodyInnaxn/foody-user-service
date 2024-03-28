package com.foody.userservice.business;

import com.foody.userservice.business.exceptions.UserNotFoundException;
import com.foody.userservice.dto.CreateUserResponse;
import com.foody.userservice.dto.UserRequest;
import com.foody.userservice.dto.UserResponse;
import com.foody.userservice.persistence.UserRepository;
import com.foody.userservice.persistence.entity.UserEntity;
import com.foody.userservice.persistence.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public CreateUserResponse createUser(UserRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(request.getFirstName());
        userEntity.setLastName(request.getLastName());
        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(request.getPassword());
        userEntity.setBio(request.getBio());
        userEntity.setProfilePicUrl(request.getProfilePicUrl());
        userEntity.setRole(UserRole.valueOf(request.getRole().toUpperCase()));

        UserEntity savedUser = userRepository.save(userEntity);

        return CreateUserResponse.builder()
                .id(savedUser.getId())
                .build();
    }

    @Override
    public UserResponse getUserById(Long id) {
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();
            UserResponse response = new UserResponse();
            response.setId(userEntity.getId());
            response.setFirstName(userEntity.getFirstName());
            response.setLastName(userEntity.getLastName());
            response.setBio(userEntity.getBio());
            response.setEmail(userEntity.getEmail());
            response.setProfilePicUrl(userEntity.getProfilePicUrl());

            return response;

        } else {
            throw new UserNotFoundException();
        }
    }

//    // Map UserEntity to UserResponse
//    private UserResponse mapToUserResponse(UserEntity userEntity) {
//        return new UserResponse(
//                userEntity.getId(),
//                userEntity.getFirstName(),
//                userEntity.getLastName(),
//                userEntity.getEmail(),
//                userEntity.getBio(),
//                userEntity.getProfilePicUrl(),
//                userEntity.getRole().toString()
//        );
//    }
}
