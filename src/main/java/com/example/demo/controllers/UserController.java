package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.serveces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/user")
@CrossOrigin(origins = "http://localhost:3000")

public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }
    @GetMapping("/ByUserName/{userName}")
    public User getUserByUserName(@PathVariable String userName){
        return userService.getUserByUserName(userName);
    }
    /*@PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }*/
    @PostMapping("/addUser")
    public void addUser(@RequestBody User user){
        userService.addUser(user);

    }
    @DeleteMapping("/delete/{userName}")
    void deleteByUserName(@PathVariable String userName){
        userService.deleteByUserName(userName);
    }
    @PutMapping("/update/{userName}")
    void updateByUserName(@PathVariable String userName,
                          @RequestParam (required = false) String firstName,
                          @RequestParam (required = false) String lastName,
                          @RequestParam (required = false) String email,
                          @RequestParam (required = false) String password,
                          @RequestParam (required = false) String userName1,
                          @RequestParam (required = false)Date dop){
    userService.updateUser(userName,firstName,lastName,email,password,userName1,dop);

    }


}
