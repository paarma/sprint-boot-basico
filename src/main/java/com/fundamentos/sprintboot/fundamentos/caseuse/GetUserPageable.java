package com.fundamentos.sprintboot.fundamentos.caseuse;

import com.fundamentos.sprintboot.fundamentos.entity.User;
import com.fundamentos.sprintboot.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetUserPageable {

    private UserService userService;

    public GetUserPageable(UserService userService) {
        this.userService = userService;
    }

    public List<User> getUserPageable(int page, int size){
        return userService.getAllUsersPageable(page, size);
    }
}
