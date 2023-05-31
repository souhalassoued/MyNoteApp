package com.MyNote.MyNote.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
@AllArgsConstructor
@Data
@Builder
@Document(collection="Note")
public class Note implements Serializable {
    private static final long serialVersionUID= 1L;
    @Id
    private String ID;
    @Indexed(unique = true)
    @NonNull
    private String titre;
    @Indexed(unique = true)
    @NonNull
    private Date d_Creation;
    @Indexed(unique = true)
    @NonNull
    private String contenue;


}
