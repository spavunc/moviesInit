package com.init.task.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryCommand {
    @NotBlank(message = "Name must not be empty")
    private String name;
}
