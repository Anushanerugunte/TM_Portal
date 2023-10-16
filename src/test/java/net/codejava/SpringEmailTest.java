package net.codejava;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

@SpringBootTest
@PropertySource("classpath:application.properties") // If you have properties to load
public class SpringEmailTest {

}
