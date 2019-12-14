package com.epam.models;

import lombok.Data;
import lombok.NonNull;

@Data
public class Person {

    @NonNull
    private String name;

    @NonNull
    private String surname;

    @NonNull
    private String email;

    @NonNull
    private Type typeOfCategory;

}
