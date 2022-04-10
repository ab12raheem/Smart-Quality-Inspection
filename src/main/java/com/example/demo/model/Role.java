package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "role",schema = "public")
public class Role {
    @Id
    private String roleName;
    @OneToOne(mappedBy = "role", cascade = CascadeType.ALL)
    private User user;

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
