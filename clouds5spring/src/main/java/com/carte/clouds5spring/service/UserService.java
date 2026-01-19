package com.carte.clouds5spring.service;

import java.util.List;

import com.carte.clouds5spring.dto.UserUpdateRequest;
import com.carte.clouds5spring.entity.User;

public interface UserService {
    void unblockUser(Integer userId);
    List<User> getBlockedUsers();
    void updateMyProfile(String emailConnected, UserUpdateRequest req);
    void updateUser(Integer userId, UserUpdateRequest req);
}
