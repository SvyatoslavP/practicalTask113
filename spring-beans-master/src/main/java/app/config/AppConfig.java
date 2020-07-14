package app.config;

import app.model.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Timer;

@Configuration
@ComponentScan(basePackages = "app")
public class AppConfig {

}
