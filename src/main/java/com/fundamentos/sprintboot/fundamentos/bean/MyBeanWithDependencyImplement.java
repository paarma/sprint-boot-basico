package com.fundamentos.sprintboot.fundamentos.bean;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency {

    //Esta sería la otra dependencia
    private MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        int numero = 1;
        System.out.println("Llamado al metodo suma de la otra dependencia: " + myOperation.suma(numero));
        System.out.println("Hola desde la implementación de un Bean con dependencia ---------------");
    }
}
