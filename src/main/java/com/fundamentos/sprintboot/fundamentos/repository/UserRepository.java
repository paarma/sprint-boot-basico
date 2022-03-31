package com.fundamentos.sprintboot.fundamentos.repository;

import com.fundamentos.sprintboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que hereda de la interfaz "JpaRepository" a la cual el enviamos como parámetros
 * "User" que indica la entidad con la que vamos a trabajar (User.java) y el parámetro
 * "Long" el cual es el tipo de dato del ID de esa entidad.
 *
 * Se es estereotipo @Repository para poder inyectar esta interfaz como dependencia.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Dentro de la anotación @Query podemos definir una consulta JPQL personalizada.
     */
    @Query("Select u from User u where u.email = ?1 ")
    Optional<User> findByUserEmail(String email);

    @Query("Select u from User u where u.name like ?1%")
    List<User> findAndSort(String name, Sort sort);

    //QueryMethod
    List<User> findByName(String name);

    //QueryMethod
    Optional<User> findByEmailAndName(String email, String name);

}
