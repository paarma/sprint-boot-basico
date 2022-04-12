package com.fundamentos.sprintboot.fundamentos.caseuse;

import com.fundamentos.sprintboot.fundamentos.entity.User;
import com.fundamentos.sprintboot.fundamentos.service.UserService;

import java.util.List;

public class GetUserImplement implements GetUser {

    private UserService userService;

    public GetUserImplement(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll() {
        return userService.getAllUsers();
    }
}
