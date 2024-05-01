package com.foody.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToReceive {

    private String firstName;
    private String lastName;
    private String bio;
    private Long authId;
}
