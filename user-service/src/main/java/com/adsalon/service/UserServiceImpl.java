package com.adsalon.service;

import com.adsalon.Exception.UserException;
import com.adsalon.model.User;
import com.adsalon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(long id) throws UserException {
        Optional<User> opt =  userRepository.findById(id);
        if(opt.isPresent()){
            return  opt.get();
        }
        throw new UserException("user not found");
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(long id) throws UserException {
        Optional<User> opt =  userRepository.findById(id);
        if(opt.isEmpty()){
            throw new UserException("user not exist with id :"+id);
        }
        userRepository.deleteById(opt.get().getId());

    }

    @Override
    public User updateUser(long id, User user) throws UserException {

        Optional<User> opt = userRepository.findById(id);
        if(opt.isEmpty()){
            throw new UserException("user not found with id : "+ id);
        }
        User existUser = opt.get();
        existUser.setFullName(user.getFullName());
        existUser.setEmail(user.getEmail());
        existUser.setRole(user.getRole());
        existUser.setPhone(user.getPhone());
        return userRepository.save(existUser);
    }
}
