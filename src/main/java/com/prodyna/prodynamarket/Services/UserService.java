package com.prodyna.prodynamarket.Services;

import com.prodyna.prodynamarket.Models.User;
import com.prodyna.prodynamarket.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //    TODO --> no duplicate user name
    public void createNewUser(User user) {
        userRepository.save(user);
    }

    public User getUser(int id) {
        if (userRepository.findById(id).isPresent())
            return userRepository.findById(id).get();
        else
            throw new NoSuchElementException();
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public User getUserByName(String userName) throws IndexOutOfBoundsException {
        return userRepository.findUserByName(userName).get(0);
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().forEach(userList::add);
        return userList;
    }

    public boolean authenticateUser(String userName, String password) {
        try {
            User user = getUserByName(userName);
            return user.getPassword().equals(password);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }
}
