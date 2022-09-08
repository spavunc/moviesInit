package com.init.task.movie;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class MovieCommand {

    @NotBlank(message = "Name must not be empty")
    private String name;
    @NotEmpty(message = "Movie must have at least one category")
    List<Long> movieCategories;
}
