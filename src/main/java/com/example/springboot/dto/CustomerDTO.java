package com.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomerDTO {
    private String name;
    private int age;
}

//DTOs are objects used to transfer data between different layers of an application, such as between
// the backend and frontend. They serve as a container for data and often include only the necessary
// fields to be transferred, excluding any business logic. DTOs can help decouple the internal data
// representation from the external interfaces, providing a clear separation between layers

//In some cases, a combination of DTOs and views may be appropriate.
// For instance, you can use DTOs to transfer data between layers,
// and then use views to render that data within the presentation layer.
