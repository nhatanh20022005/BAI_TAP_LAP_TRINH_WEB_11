package vn.bt10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the BaiTap10 application.  This class uses Spring Boot's
 * auto‑configuration to bootstrap the web application and register the
 * components defined in this project.  Running the main method will
 * start an embedded Tomcat server on the default port (8080).
 */
@SpringBootApplication
public class BaiTap10Application {

    public static void main(String[] args) {
        SpringApplication.run(BaiTap10Application.class, args);
    }
}