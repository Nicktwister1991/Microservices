package ru.skillbox.demo.Controller;

import org.springframework.web.bind.annotation.*;
import ru.skillbox.demo.entity.User;
import ru.skillbox.demo.service.UserService;


@RestController
@RequestMapping (value = "/users")
public class UserController {
    private final UserService userservice;

    public UserController(UserService userservice) {
        this.userservice = userservice;
    }

    @PostMapping
    String CreateUser (@RequestBody User user){
        return userservice.createUser(user);

    }
    @GetMapping (path = "/{id}")
    User getUser (@PathVariable long id){
        return userservice.getUser(id);

    }
    @PutMapping (path = "/{id}")
    String updateUser (@RequestBody User user, @PathVariable long id){
        return userservice.updateUser(user,id);
    }

    @DeleteMapping (path = "/{id}")
    String deleteUser (@PathVariable long id){
        return  userservice.deleteUser(id);

    }
    @PostMapping("/{user_id}/addFriend/{friend_id}")
    Long addFriend(@PathVariable("user_id") Long userId, @PathVariable("friend_id") Long friendId) {
        return userservice.addFriend(userId, friendId);
    }
}
