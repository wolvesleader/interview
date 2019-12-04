package com.quincy.java.spi;

import org.junit.Test;

import java.util.ServiceLoader;

/**
 * author:quincy
 * Date:2019-11-21
 */
public class SPITestDriver {


    @Test
    public void testSPI(){
        ServiceLoader<Person> serviceLoader = ServiceLoader.load(Person.class);

        for (Person p: serviceLoader) {
            System.out.println(p.getName("1"));
        }

    }
}
