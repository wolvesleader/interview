package com.quincy.java.spi;

import org.junit.Test;

import java.util.ServiceLoader;

public class SPITestDriver {


    @Test
    public void testSPI(){
        ServiceLoader<Person> serviceLoader = ServiceLoader.load(Person.class);

        for (Person p: serviceLoader) {
            System.out.println(p.getName("1"));
        }

    }
}
