package com.epam.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    private String email;
    @NonNull
    private Type typeOfCategory;

    public Person(@NonNull String name, @NonNull String surname, @NonNull String email, @NonNull Type typeOfCategory) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.typeOfCategory = typeOfCategory;
    }


}
