package com.init.task.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.init.task.category.Category;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long movieId;
    private String name;

    Movie(String name, List<Category> movieCategories){
        this.name = name;
        this.movieCategories = movieCategories;
    }

    @JsonIgnoreProperties("movies")
    @ManyToMany(targetEntity = Category.class, fetch= FetchType.EAGER)
    @JoinTable(name="Movie_Category",
            joinColumns = @JoinColumn(name="movieId"),
            inverseJoinColumns = @JoinColumn(name="categoryId"))
    List<Category> movieCategories;
}
