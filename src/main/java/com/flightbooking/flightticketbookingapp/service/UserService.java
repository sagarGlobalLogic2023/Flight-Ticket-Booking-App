package com.flightbooking.flightticketbookingapp.service;

import com.flightbooking.flightticketbookingapp.entity.Booking;
import com.flightbooking.flightticketbookingapp.entity.Plane;
import com.flightbooking.flightticketbookingapp.payload.UpdateProfilePayload;
import com.flightbooking.flightticketbookingapp.payload.UpdateUserPayload;
import com.flightbooking.flightticketbookingapp.repository.BookingRepo;
import com.flightbooking.flightticketbookingapp.repository.UserRepo;
import com.flightbooking.flightticketbookingapp.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    public String updateUser(UpdateUserPayload updateUserPayload) {

        User user = new User();

        Optional<User> updatedUser = userRepo.findById(updateUserPayload.getUserId());
        if (updatedUser.isPresent()) {
            if (updatedUser.get().getRole().equals("user")) {

                Long userId = updatedUser.get().getUserId();
                user.setUserId(userId);

                user.setEmail(updateUserPayload.getEmail());
                user.setPassword(updateUserPayload.getPassword());
                user.setFirstName(updateUserPayload.getFirstName());
                user.setLastName(updateUserPayload.getLastName());
                user.setIsBlocked(updateUserPayload.getIsBlocked());
                user.setRole(updateUserPayload.getRole());

                userRepo.save(user);
                return "User details updated";
            }
        }
            String str= "User Id invalid";
        return str;


    }
    public List<User> listAllUsers() {

        List<User> allUsers= userRepo.findAll();
        return allUsers;

    }
    public String blockUser(String status, Long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            if (user.get().getRole().equals("user")) {
                userRepo.changeUserStatus(status, id);
                return "User Blocked Sucessfully";
            }
            return "User Id invalid";
        }
        return "User does not Exit";
    }

    public User getUser(Long userId) {
        return  userRepo.findById(userId).get();
        //return userRepo.getUserById(userId);
    }

    public User updateProfile(UpdateProfilePayload updateProfilePayload){
        User user= new User();
        user.setUserId(updateProfilePayload.getUserId());
        Optional<User> userObj= userRepo.findById(user.getUserId());
        user.setEmail(updateProfilePayload.getEmail());
        user.setPassword(updateProfilePayload.getPassword());
        user.setFirstName(updateProfilePayload.getFirstName());
        user.setLastName(updateProfilePayload.getLastName());
        user.setIsBlocked(userObj.get().getIsBlocked());
        user.setRole(userObj.get().getRole());
        userRepo.save(user);
        return userRepo.findById(user.getUserId()).get();
    }
}

