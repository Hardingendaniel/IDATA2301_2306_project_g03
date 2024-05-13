package no.ntnu.webappgroup03.controllers;

import no.ntnu.webappgroup03.model.User;
import no.ntnu.webappgroup03.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping
public class UserController {

  private Map<Integer, User> users;
  @Autowired
  private UserService userService;


  /**
   * Get all users.
   * HTTP GET to /
   *
   * @return List of all users currently stored in the collection
   */
  @GetMapping("/user/")
  public Collection<User> getAll() {
    return users.values();
  }


  @GetMapping
  public Iterable<User> getAllUsers() {
    return userService.getAll();
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable int id) {
    return userService.findUserById(id);
  }



  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable int id) {
    userService.deleteUser(id);
    return ResponseEntity.ok().build();
  }

}
