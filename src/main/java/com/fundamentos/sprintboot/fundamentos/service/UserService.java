package com.fundamentos.sprintboot.fundamentos.service;

import com.fundamentos.sprintboot.fundamentos.entity.User;
import com.fundamentos.sprintboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private final Log LOG = LogFactory.getLog(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * peek() Se utiliza para poder ver paso a paso las acciones que hacemos
     * en un stream(). Podemos verlas por ejemplo con impresiones en consola.
     *
     * @Transactional permite hacer rollback de los registros que se iban a insertar.
     * Por ejemplo si de la lista de usuarios que estamos insertando falla alguno, el sistema
     * hace rollbakc de todos los usuarios.
     */
    @Transactional
    public void saveTransactional(List<User> users){
        users.stream()
                .peek(user -> LOG.info("peek() Usuario insertado: "+user))
                .forEach(userRepository::save);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
