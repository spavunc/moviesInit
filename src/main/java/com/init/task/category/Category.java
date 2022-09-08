package com.init.task.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.init.task.movie.Movie;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long categoryId;
    private String name;

    Category(String name, List<Movie> movies){
        this.name = name;
        this.movies = movies;
    }

    public Category(Long categoryId, String name){
        this.categoryId = categoryId;
        this.name = name;
    }

    public Category(String name){
        this.name = name;
    }

    @ManyToMany(targetEntity = Movie.class, mappedBy = "movieCategories")
    @JsonIgnoreProperties("movieCategories")
    List<Movie> movies;
}
