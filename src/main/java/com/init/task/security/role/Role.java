package com.init.task.security.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.init.task.security.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @Column(name="id")
    private Long roleId;
    private String name;

    public Role(Long roleId, String name){
        this.roleId = roleId;
        this.name = name;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    List<User> users;
}
