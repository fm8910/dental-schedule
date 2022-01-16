package ni.com.nicasource.model;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import lombok.Data;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@UserDefinition
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", nullable = false)
    @Username
    private String username;

    @Column(name = "password", nullable = false)
    @Password
    @JsonbTransient
    private String password;

    @Column(name = "role", nullable = false)
    @Roles
    private String role;

    @Column(name = "complete_names", nullable = false)
    private String names;
}
