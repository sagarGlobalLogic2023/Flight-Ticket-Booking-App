package com.flightbooking.flightticketbookingapp.service;

import com.flightbooking.flightticketbookingapp.payload.UpdateProfilePayload;
import com.flightbooking.flightticketbookingapp.payload.UpdateUserPayload;
import com.flightbooking.flightticketbookingapp.repository.UserRepo;
import com.flightbooking.flightticketbookingapp.user.Role;
import com.flightbooking.flightticketbookingapp.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public String updateUser(UpdateUserPayload updateUserPayload) {

        User user = new User();

        Optional<User> updatedUser = userRepo.findById(updateUserPayload.getUserId());
        if (updatedUser.isPresent()) {
            if (updatedUser.get().getRole().equals(Role.ROLE_USER)) {
                user.setUserId(updateUserPayload.getUserId());
                user.setEmail(updateUserPayload.getEmail());
                user.setPassword(passwordEncoder.encode(updateUserPayload.getPassword()));
                user.setFirstName(updateUserPayload.getFirstName());
                user.setLastName(updateUserPayload.getLastName());
                user.setIsBlocked(updateUserPayload.getIsBlocked());
                user.setRole(Role.valueOf((updateUserPayload.getRole())));

                userRepo.save(user);
                return "User Details Updated";
            }
        }
            return "User Not Found";
    }
    public List<User> listAllUsers() {

        List<User> allUsers = userRepo.findAll();
        List<User>onlyUsers= new ArrayList<>();
        for (User allUser : allUsers) {
            if (allUser.getRole().equals(Role.ROLE_USER)) {
                onlyUsers.add(allUser);
            }
        }
        return onlyUsers;

    }
    public String blockUser(String status, Long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            if (user.get().getRole().equals(Role.ROLE_USER)) {
                userRepo.changeUserStatus(status, id);
                return "User Blocked Successfully";
            }
            return "User Id invalid";
        }
        return "User does not Exist";
    }

    public User getUser(Long userId) {
        return  userRepo.findById(userId).get();
    }

    public String updateProfile(UpdateProfilePayload updateProfilePayload){
        User user= new User();
        Optional<User> userObj= userRepo.findById(updateProfilePayload.getUserId());
            if (userObj.isPresent()) {
                if(userObj.get().getRole().equals(Role.ROLE_USER)) {
                user.setUserId(updateProfilePayload.getUserId());
                user.setEmail(updateProfilePayload.getEmail());
                user.setPassword(passwordEncoder.encode(updateProfilePayload.getPassword()));
                user.setFirstName(updateProfilePayload.getFirstName());
                user.setLastName(updateProfilePayload.getLastName());
                user.setIsBlocked(userObj.get().getIsBlocked());
                user.setRole(userObj.get().getRole());
                userRepo.save(user);
                return "Profile updated successfully";
            }
        }
        return "Error Updating Profile, user not found";


    }
}

