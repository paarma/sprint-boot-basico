package com.fundamentos.sprintboot.fundamentos.configuration;

import com.fundamentos.sprintboot.fundamentos.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Se agrega la notación @Configuration para indicarle a sprint
 * que en esta clase se encuentra una configuración de beans personalizada.
 */
@Configuration
public class MyConfigurationBean {

    @Bean
    public MyBean beanOperation(){
        //Se llama a la implementación
        //return new MyBeanImplement();

        //Se llama a la Segunda implementación
        return new MyBeanTwoImplement();
    }

    @Bean
    public MyOperation beanOperationOperation(){
        return new MyOperationImplement();
    }

    //Se envía como parametro la dependencia que usa el constructor de "MyBeanWithDependencyImplement"
    @Bean
    public MyBeanWithDependency beanOperationWithDependency(MyOperation myOperation){
        return new MyBeanWithDependencyImplement(myOperation);
    }
}
