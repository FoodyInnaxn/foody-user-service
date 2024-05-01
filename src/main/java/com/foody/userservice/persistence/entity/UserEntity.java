package com.foody.userservice.persistence.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "user_entity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "auth_id", nullable = false)
    private Long authId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(length = 1000)
    private String bio;

    @Column(name = "profile_pic_url")
    private String profilePicUrl;

}

