package uz.ilmnajot.register_3_with_jwt_and_thymeleaf.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.enums.Authority;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ROLES")
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Authority> authorities;
}
