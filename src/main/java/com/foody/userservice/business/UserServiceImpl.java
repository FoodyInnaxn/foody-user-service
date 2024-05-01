package com.foody.userservice.business;

import com.foody.userservice.business.exceptions.UserNotFoundException;
import com.foody.userservice.business.rabbitmq.UserEventPublisher;
import com.foody.userservice.configuration.RabbitMQConfig;
import com.foody.userservice.dto.*;
import com.foody.userservice.persistence.UserRepository;
import com.foody.userservice.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserEventPublisher eventPublisher;

    @Override
    @RabbitListener(queues = RabbitMQConfig.AUTH_QUEUE)
    public void createUser(UserToReceive request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(request.getFirstName());
        userEntity.setLastName(request.getLastName());
        userEntity.setBio(request.getBio());
        userEntity.setAuthId(request.getAuthId());

       userRepository.save(userEntity);
    }

    @Override
    public UserInfo getUserById(Long authId) {
        Optional<UserEntity> userOptional = userRepository.findByAuthId(authId);
        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();
            UserInfo response = new UserInfo();
//            response.setId(userEntity.getId());
//            response.setAuthId(userEntity.getAuthId());
            response.setFirstName(userEntity.getFirstName());
            response.setLastName(userEntity.getLastName());
            response.setBio(userEntity.getBio());
//            response.setProfilePicUrl(userEntity.getProfilePicUrl());

            return response;

        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
        eventPublisher.publishDelete(id);
    }

    @Override
    public void updateUser(Long id, UpdateBasicUserRequest request) {

        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        existingUser.setBio(request.getBio());
        existingUser.setFirstName(request.getFirstName());
        existingUser.setLastName(request.getLastName());

        userRepository.save(existingUser);
    }
}
