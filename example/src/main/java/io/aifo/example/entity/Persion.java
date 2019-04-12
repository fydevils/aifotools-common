package io.aifo.example.entity;


import io.aifo.api.javassist.Inject;
import io.aifo.api.apt.InstanceFactory;

@InstanceFactory
public class Persion {

    @Inject
    public School school;

    public Persion(){
    }

    public void printSelf() {
        System.out.println("I got you here ;!");
    }

}
