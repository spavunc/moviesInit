package com.init.task.security.user;

import com.init.task.security.role.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long userId;
    private String name;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    Role role;

    public User(String name, String password, Role role){
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public User(String name, String password){
        this.name = name;
        this.password = password;
    }
}
