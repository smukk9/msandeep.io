package io.sandeep.blog.entity;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
public class Role {


    private int id;
    private String role;

}
