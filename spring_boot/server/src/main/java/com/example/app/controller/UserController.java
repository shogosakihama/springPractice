package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.app.repository.UserRepository;
import com.example.app.entity.User;

import java.io.File;
import java.util.Optional;

@Controller
public class UserController {

  private final UserRepository repository;

  @Autowired
  public UserController(UserRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/form")
  public String showForm() {
    return "userForm"; // userForm.htmlを表示
  }

  @PostMapping("/user")
  public String getUserNameByIdPost(@RequestParam Long id, Model model) {
    Optional<User> user = repository.findById(id);
    String userName = user.map(User::getName).orElse("User not found");
    model.addAttribute("userName", userName);
    return "userResult"; // userResult.htmlを表示
  }

  @GetMapping("/user")
  public String getUserNameById(@RequestParam Long id, Model model) {
      Optional<User> user = repository.findById(id);
      String userName = user.map(User::getName).orElse("User not found");
      model.addAttribute("userName", userName);
      return "userResult"; // userResult.htmlを表示
  }

  @GetMapping("/disk-space")
  public String getDiskSpace(Model model) {
    File cDrive = new File("C:");

    long totalSpace = cDrive.getTotalSpace();
    long freeSpace = cDrive.getFreeSpace();
    long usableSpace = cDrive.getUsableSpace();

    model.addAttribute("totalSpace", String.format("%.2f", totalSpace / 1e9));
    model.addAttribute("freeSpace", String.format("%.2f", freeSpace / 1e9));
    model.addAttribute("usableSpace", String.format("%.2f", usableSpace / 1e9));

    return "diskSpace";
  }
  
}
