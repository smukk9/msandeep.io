package io.sandeep.blog.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Data
@Entity
@NoArgsConstructor
public class User {

    private int id;
    private String username;
    private String password;
    private String email;

}
