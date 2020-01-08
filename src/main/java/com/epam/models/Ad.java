package com.epam.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.UUID;

@XmlRootElement
@Data
@NoArgsConstructor
public class Ad {
    @NonNull
    private String id = String.valueOf(UUID.randomUUID());
    @NonNull
    private int person_id;

    private Person person;
    @NonNull
    private String title;
    @NonNull
    private String body;
    @NonNull
    private Category category;
    @NonNull
    private String phone;
    @NonNull
    private LocalDate dateOfCreation = LocalDate.now();

    public Ad(@NonNull int person_id, @NonNull String title, @NonNull String body, @NonNull Category category,
              @NonNull String phone) {
        this.person_id = person_id;
        this.title = title;
        this.body = body;
        this.category = category;
        this.phone = phone;
    }

}
