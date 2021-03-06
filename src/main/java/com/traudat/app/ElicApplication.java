package com.traudat.app;
import com.traudat.app.repo.impl.BaseRepositoryImpl;
import com.traudat.app.views.Login;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class ElicApplication extends Application{

    private static ConfigurableApplicationContext applicationContext;

    @Override
    public void init() throws Exception {
        this.applicationContext = SpringApplication.run(ElicApplication.class);
    }

    @Override
    public void stop() throws Exception {
        applicationContext.close();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
          Login.loadView(stage);
    }

    public static ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
