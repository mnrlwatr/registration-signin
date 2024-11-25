package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class User implements Serializable {
    private UUID id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
}
