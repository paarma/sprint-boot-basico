package com.fundamentos.sprintboot.fundamentos;

import com.fundamentos.sprintboot.fundamentos.bean.MyBean;
import com.fundamentos.sprintboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.sprintboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.sprintboot.fundamentos.component.ComponentDependency;
import com.fundamentos.sprintboot.fundamentos.entity.User;
import com.fundamentos.sprintboot.fundamentos.pojo.UserPojo;
import com.fundamentos.sprintboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

	//Se inyecta la dependencia en el constructor (Se inyecta la interfaz)
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
								  MyBean myBean, MyBeanWithDependency myBeanWithDependency,
								  MyBeanWithProperties myBeanWithProperties,
								  UserPojo userPojo,
								  UserRepository userRepository){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ejemplosImpresiones();
		saveUsersInDatabase();
	}

	private void saveUsersInDatabase(){
		User user = new User("Juan", "juan@domain.com",
				LocalDate.of(2020, 03, 23));

		User user2 = new User("Ana", "ana@domain.com",
				LocalDate.of(2021, 04, 24));

		User user3 = new User("Julia", "julia@domain.com",
				LocalDate.of(2022, 03, 25));

		List<User> list = Arrays.asList(user, user2, user3);

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
