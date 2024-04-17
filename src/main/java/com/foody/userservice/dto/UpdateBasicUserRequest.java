package com.foody.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBasicUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String bio;
}