package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.app.repository.UserRepository;
import com.example.app.entity.User;

import java.util.Optional;

@RestController
public class UserController {

  private final UserRepository repository;

  @Autowired
  public UserController(UserRepository repository) {
    this.repository = repository;
  }

  @RequestMapping("/")
  public String user() {
    return String.valueOf(repository.findAll());
  }

  @GetMapping("/user")
    public String getUserNameById(@RequestParam Long id) {
        Optional<User> user = repository.findById(id);
        return user.map(User::getName).orElse("User not found");
    }
}
