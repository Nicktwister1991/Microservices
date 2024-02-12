package ru.skillbox.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skillbox.demo.entity.User;
import ru.skillbox.demo.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(long id) {
       return userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String createUser (User user){
       User savedUser =  userRepository.save(user);
       return String.format("Пользователь %s добавлен в базу с id = %s",savedUser.getLastName(),savedUser.getId());
    }

    public String updateUser(User user, long id) {
        if(!userRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        User savedUser = userRepository.save(user);
        return String.format("Пользователь %s успешно сохранен",savedUser.getLastName());
    }

    public String deleteUser(long id) {
        if(!userRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        userRepository.deleteById(id);
        return  String.format( "Пользователь c id = %s  успешно удален",id);
    }
    public Long addFriend(Long userId, Long friendId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден"));
        User friend = userRepository.findById(friendId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Друг не найден"));

        if (user.getFriends().contains(friend)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Пользователь уже есть в списке друзей");
        }

        user.getFriends().add(friend);
        friend.getFriends().add(user);

        userRepository.save(user);
        userRepository.save(friend);

        return user.getId();
    }
}
