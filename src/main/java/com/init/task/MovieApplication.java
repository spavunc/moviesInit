package com.init.task;

import com.init.task.category.Category;
import com.init.task.category.CategoryRepository;
import com.init.task.movie.MovieRepository;
import com.init.task.security.role.Role;
import com.init.task.security.role.RoleEnum;
import com.init.task.security.role.RoleRepository;
import com.init.task.security.user.User;
import com.init.task.security.user.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieApplication {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MovieRepository movieRepository;

    MovieApplication(CategoryRepository categoryRepository, UserRepository userRepository,
                     RoleRepository roleRepository, MovieRepository movieRepository){
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.movieRepository = movieRepository;

    }

    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }


    @Bean
    InitializingBean resetDatabase() {
        return () -> {
            categoryRepository.deleteAll();
            movieRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();
            categoryRepository.save(new Category("Drama"));
            Role admin = roleRepository.save((new Role(Long.valueOf(1), RoleEnum.ROLE_ADMIN.name())));
            //pass is 1234567890
            userRepository.save(new User("randomAdmin", "$2a$10$XMg3grfB1b2soRbNOwKEa.H/vwgVxGex8P1n0j93lUNBV2fc2xvIa", admin));
            Role user = roleRepository.save((new Role(Long.valueOf(2), RoleEnum.ROLE_USER.name())));
            userRepository.save(new User("randomUser", "$2a$10$XMg3grfB1b2soRbNOwKEa.H/vwgVxGex8P1n0j93lUNBV2fc2xvIa", user));
        };
    }
}
