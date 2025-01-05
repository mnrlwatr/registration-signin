package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    private UUID id;
    @Column(name = "email",unique = true,nullable = false,length = 25)
    private String email;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "firstname",length = 20)
    private String firstname;
    @Column(name = "lastname",length = 20)
    private String lastname;
}
