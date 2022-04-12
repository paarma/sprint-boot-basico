package com.fundamentos.sprintboot.fundamentos;

import com.fundamentos.sprintboot.fundamentos.bean.MyBean;
import com.fundamentos.sprintboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.sprintboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.sprintboot.fundamentos.component.ComponentDependency;
import com.fundamentos.sprintboot.fundamentos.entity.User;
import com.fundamentos.sprintboot.fundamentos.pojo.UserPojo;
import com.fundamentos.sprintboot.fundamentos.repository.UserRepository;
import com.fundamentos.sprintboot.fundamentos.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Con la implementación de la interfaz "CommandLineRunner" podemos sobreescribir el método
 * "Run" el cual ejecutará todas las instrucciones que pongamos en él al ejecutar el programa.
 */
@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;

	//Se inyecta la dependencia en el constructor (Se inyecta la interfaz)
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
								  MyBean myBean, MyBeanWithDependency myBeanWithDependency,
								  MyBeanWithProperties myBeanWithProperties,
								  UserPojo userPojo,
								  UserRepository userRepository,
								  UserService userService){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ejemplosImpresiones();
		saveUsersInDatabase();
		getInformationJpqlFromUser();
		saveWithErrorTransctional();
	}

	private void saveWithErrorTransctional(){
		User test1 = new User("test1", "user1@domain.com", LocalDate.now());
		User test2 = new User("test2", "user2@domain.com", LocalDate.now());
		User test3 = new User("test3", "user1@domain.com", LocalDate.now());

		List<User> users = Arrays.asList(test1, test2, test3);

		try {
			//Guardamos los usuarios en BD
			userService.saveTransactional(users);
		}catch (Exception e){
			LOGGER.error("Esta es una exception dentro del metodo transactional " + e);
		}

		//Obtenemos los usuarios de la BD
		userService.getAllUsers().stream()
				.forEach(user -> LOGGER.info("Usuario obtenido de la BD: "+user));
	}

	private void getInformationJpqlFromUser(){
		LOGGER.info("Usuario con el metodo findByUserEmail "+userRepository
				.findByUserEmail("ana@domain.com")
				.orElseThrow(() -> new RuntimeException("No se encontró el usuario.")));

		//Usando foreach CON stream
		/*userRepository.findAndSort("Ju", Sort.by("id").ascending())
			.stream()
			.forEach(user -> LOGGER.info("Usuario con metodo sort "+user));
		;*/

		//Usando solo foreach SIN stream
		userRepository.findAndSort("Ju", Sort.by("id").ascending())
				.forEach(LOGGER::info);

		//Llamando a queryMethod con UN solo parametro
		System.out.println("xxxxxxxxxxxxxxxxxxxxxx Lista de usuarios con queryMethod: ");
		userRepository.findByName("Ana").forEach(LOGGER::info);

		//Llamando a queryMethod con DOS parametro
		LOGGER.info("xxxxxxxxxxxxxxxxxxxxxx Usuario con queryMethod con dos parametros : "+
				userRepository.findByEmailAndName("julia@domain.com", "Julia")
						.orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

		//Llamando a queryMethod con "LIKE"
		userRepository.findByNameLike("%An%")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameLike: " + user));

		//Llamando a queryMethod con "OR"
		userRepository.findByNameOrEmail(null, "julia@domain.com")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameOrEmail: " + user));

		//Llamando a queryMethod con "Between"
		userRepository.findByBirthDateBetween(LocalDate.of(2020,01,01),
				LocalDate.of(2020, 12, 30))
				.stream()
				.forEach(user -> LOGGER.info("Usuarios entre intervalo de fechas: " + user));

		//Llamando a queryMethod con "LIKE" y "ORDER BY"
		userRepository.findByNameLikeOrderByIdDesc("%Ju%")
				.stream()
				.forEach(user -> LOGGER.info("Usuarios con LIKE y ORDER BY: " + user));

		//Llamando a queryMethod con "Containing" (No necesita los caracteres "%" del LIKE)
		userRepository.findByNameContainingOrderByIdDesc("Ju")
				.stream()
				.forEach(user -> LOGGER.info("Usuarios con Containing y ORDER BY: " + user));

		//Llamado a JPQL con namedParameters
		LOGGER.info("Usuario con namedParameters: " + userRepository.getAllByBirthDateAndEmail(
				LocalDate.of(2021, 04, 24), "ana@domain.com"
		).orElseThrow(() -> new RuntimeException("No se encotró el usuario")));

	}

	private void saveUsersInDatabase(){
		User user = new User("Juan", "juan@domain.com",
				LocalDate.of(2020, 03, 23));

		User user2 = new User("Ana", "ana@domain.com",
				LocalDate.of(2021, 04, 24));

		User user3 = new User("Julia", "julia@domain.com",
				LocalDate.of(2022, 03, 25));

		User user4 = new User("Ana", "ana222@domain.com",
				LocalDate.of(2020, 02, 26));

		List<User> list = Arrays.asList(user, user2, user3, user4);

		//Se registra en BD todos los usuarios
		list.stream().forEach(userRepository::save);
	}

	private void ejemplosImpresiones(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println("Valores del archivo de propiedades: "+myBeanWithProperties.function());
		System.out.println("Valores de propiedades referenciadas desde clase de propiedades: "+userPojo.getEmail() +
				" "+userPojo.getPassword());

		//Ejemplo impresion de un log tipo error
		LOGGER.error("Impresion de log tipo error");
	}
}
