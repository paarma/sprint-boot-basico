package com.fundamentos.sprintboot.fundamentos;

import com.fundamentos.sprintboot.fundamentos.component.ComponentDependency;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Con la implementación de la interfaz "CommandLineRunner" podemos sobreescribir el método
 * "Run" el cual ejecutará todas las instrucciones que pongamos en él al ejecutar el programa.
 */
@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private ComponentDependency componentDependency;

	//Se inyecta la dependencia en el constructor (Se inyecta la interfaz)
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency){
		this.componentDependency = componentDependency;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		componentDependency.saludar();
	}
}
