package com.traudat.app;

import com.traudat.app.entity.Account;
import com.traudat.app.entity.Category;
import com.traudat.app.repo.AccountRepository;
import com.traudat.app.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Autowired
    private CategoryRepository repo;
    @Autowired
    private AccountRepository accountRepository;

    @Bean
    public CommandLineRunner getCommandLineRunner() {
        return args -> {
            repo.save(new Category("Food"));
            repo.save(new Category("Meat"));
            repo.save(new Category("Drinks"));

            Account admin = new Account();
            admin.setLoginId("admin");
            admin.setName("Admin");
            admin.setRole(Account.Role.Admin);
            admin.setPassword("admin");

            accountRepository.save(admin);
        };
    }
}
