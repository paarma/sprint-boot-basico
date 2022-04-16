package com.fundamentos.sprintboot.fundamentos.service;

import com.fundamentos.sprintboot.fundamentos.entity.User;
import com.fundamentos.sprintboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.PageRequest;
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

    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    /**
     * En este caso eliminamos la entidad que tenga el id que pasamos
     * como parametro al constructor
     */
    public void delete(Long id) {
        userRepository.delete(new User(id));
    }

    /**
     * Se busca el usuaro por ID y en caso de contrarlo se setean los nuevos valores,
     * se guarda el usuario con los nuevos datos y se retorna.
     *
     * Se usa el "get" para obtener esa entidad o también se puede trabajar con el Optional
     * en el tipo de retorno del método.
     */
    public User update(User newUser, Long id) {
        return  userRepository.findById(id)
                .map(
                        user -> {
                            user.setEmail(newUser.getEmail());
                            user.setBirthDate(newUser.getBirthDate());
                            user.setName(newUser.getName());
                            return userRepository.save(user);
                        }
                ).get();
    }

    /**
     * Listado de usuarios paginados.
     * @param page Indica la pagina a mostrar.
     * @param size Indica la cantidad de usuarios a mostrar por página.
     * @return Lista de usuarios paginados.
     */
    public List<User> getAllUsersPageable(int page, int size){
        return userRepository.findAll(PageRequest.of(page, size)).getContent();
    }
}
