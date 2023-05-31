package com.MyNote.MyNote.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(collection="ToDoListe")
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class ToDoListe implements Serializable {

    private static final long serialVersionUID= 1L;
    @Id
    private String ID;
    @Indexed(unique = true)
    @NonNull
    private String task;
    @Indexed(unique = true)
    @NonNull
    private Date D_Creation;
    @Indexed(unique = true)
    @NonNull
    private Date Echeance;

}
