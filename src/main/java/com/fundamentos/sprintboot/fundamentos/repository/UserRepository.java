package com.fundamentos.sprintboot.fundamentos.repository;

import com.fundamentos.sprintboot.fundamentos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz que hereda de la interfaz "JpaRepository" a la cual el enviamos como parámetros
 * "User" que indica la entidad con la que vamos a trabajar (User.java) y el parámetro
 * "Long" el cual es el tipo de dato del ID de esa entidad.
 *
 * Se es estereotipo @Repository para poder inyectar esta interfaz como dependencia.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
