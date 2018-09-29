package com.jane.config;

import com.jane.model.Person;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * Created by Janus on 2018/9/18.
 */
@Configuration
public class MyConfig {

    @Scope("singleton")
    @Bean(name="person",initMethod="init",destroyMethod="cleanUp",
            autowire= Autowire.BY_NAME)
    public Person person01(){
        return new Person("lisi", 20);
    }
}
