package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="user",schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer Id;
    private  String firstName;
    private  String lastName;
    @Column(unique = true)
    private  String userName;
    @Column(unique = true)
    private String email ;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dop;
    @Column(nullable = false)
    private String password;
    @OneToOne
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Customer customer;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Employee employee;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Supplier supplier;
    /*@ManyToMany(fetch =FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = {
                    @JoinColumn(name = "user_name")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_name")
            }
    )
    private Set<Role> roleSet=new HashSet<>();
*/
    public User() {
    }

    public User(String firstName, String lastName,
                String userName, String email, Date dop, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.dop = dop;
        this.password = password;
        this.role = role;
    }

    public Integer getId() {
        return Id;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDop() {
        return dop;
    }

    public void setDop(Date dop) {
        this.dop = dop;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

  /*  public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }
*/
    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", dop=" + dop +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Id == user.Id && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(userName, user.userName) && Objects.equals(email, user.email) && Objects.equals(dop, user.dop) && Objects.equals(password, user.password) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, firstName, lastName, userName, email, dop, password, role);
    }
}
