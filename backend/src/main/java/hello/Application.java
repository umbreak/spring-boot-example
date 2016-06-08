package hello;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.CurrencyCode;
import hello.jpa.dao.*;
import hello.jpa.model.InformationProviderEntity;
import hello.jpa.model.RoleEntity;
import hello.jpa.model.SurveyEntity;
import hello.jpa.model.TargetEntity;
import hello.utils.DatabaseInitialization;
import model.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.SQLException;
import java.util.*;

@SpringBootApplication
@EnableScheduling
public class Application {

    @Bean(initMethod="start",destroyMethod="stop")
    public org.h2.tools.Server h2WebConsonleServer () throws SQLException {
        return org.h2.tools.Server.createWebServer("-web","-webAllowOthers","-webDaemon","-webPort", "8082");
    }

    @Bean
    CommandLineRunner init(SurveyDAO surveyRepository, TargetDAO targetRepository, InformationProviderDAO informationProviderRepository, RoleDAO roleRepository, InformationRequesterDAO userRepository) {
        return (evt) ->{
            new DatabaseInitialization(surveyRepository,targetRepository,informationProviderRepository,userRepository, roleRepository).init();
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
