package com.fundamentos.sprintboot.fundamentos.controller;

import com.fundamentos.sprintboot.fundamentos.caseuse.*;
import com.fundamentos.sprintboot.fundamentos.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private GetUser getUser;
    private CreateUser createUser;
    private DeleteUser deleteUser;
    private UpdateUser updateUser;
    private GetUserPageable getUserPageable;

    public UserRestController(GetUser getUser, CreateUser createUser,
                              DeleteUser deleteUser,
                              UpdateUser updateUser,
                              GetUserPageable getUserPageable) {
        this.getUser = getUser;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;
        this.getUserPageable = getUserPageable;
    }

    @GetMapping("/")
    List<User> get(){
        return getUser.getAll();
    }

    @PostMapping("/")
    ResponseEntity<User> newUser(@RequestBody User newUser){
        return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
    }

    /**
     * El parametro "id" será el ID del usuario a eliminar
     */
    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Long id){
        deleteUser.remove(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * El parametro "id" será el ID del usuario a editar
     * El parametro "newUser" serán los datos del usuario a actualizar.
     */
    @PutMapping("/{id}")
    ResponseEntity replaceUser(@RequestBody User newUser, @PathVariable Long id){
        return new ResponseEntity<>(updateUser.update(newUser, id), HttpStatus.OK);
    }

    @GetMapping("/pageable")
    List<User> getUserPageable(@RequestParam int page, @RequestParam int size){
        return getUserPageable.getUserPageable(page, size);
    }
}
