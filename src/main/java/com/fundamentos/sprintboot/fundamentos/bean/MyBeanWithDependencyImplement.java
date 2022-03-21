package com.fundamentos.sprintboot.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency {

    Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);

    //Esta sería la otra dependencia
    private MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        LOGGER.info("Impresion de log tipo info");
        int numero = 1;
        LOGGER.debug("Impresion de log tipo debug. Valor del parametro numero = "+numero);
        System.out.println("Llamado al metodo suma de la otra dependencia: " + myOperation.suma(numero));
        System.out.println("Hola desde la implementación de un Bean con dependencia ---------------");
    }
}
