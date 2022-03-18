package com.fundamentos.sprintboot.fundamentos.configuration;

import com.fundamentos.sprintboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.sprintboot.fundamentos.bean.MyBeanWithPropertiesImplement;
import com.fundamentos.sprintboot.fundamentos.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(UserPojo.class)
public class GeneralConfiguration {

    /**
     * Con "@Value()" podemos llamar a las variables que definimos en el archivo de propiedades
     * application.properties
     */
    @Value("${value.nombre}")
    private String nombre;

    @Value("${value.apellido}")
    private String apellido;

    @Value("${value.random}")
    private String random;

    //Hacemos el llamado a un Bean para usar estos atributos (propiedades)
    @Bean
    public MyBeanWithProperties function(){
        return new MyBeanWithPropertiesImplement(nombre, apellido);
    }
}
