package com.MyNote.MyNote.DTO;


import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Data;
        import lombok.NoArgsConstructor;
        import net.minidev.json.annotate.JsonIgnore;
        import org.springframework.data.mongodb.core.index.Indexed;
        import org.springframework.stereotype.Component;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class EmployeDto {
    private String Username;
    @Indexed
    private String email;
    private String PhoneNumber;
    @JsonIgnore
    private String password;
}
