package io.sandeep.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Data
@Entity
@NoArgsConstructor
@Table(name = "user")
@Builder
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    @NotNull
    private int id;

    @Column(name = "username")
    @NotNull
    private String username;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "active")
    @NotNull
    private int active;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

     User(User users){

        this.active =users.getActive();
        this.email =users.getEmail();
        this.username=users.getUsername();
        this.password = users.getPassword();
        this.id=users.getId();
        this.roles = users.getRoles();
    }
}
