package uz.ilmnajot.register_3_with_jwt_and_thymeleaf.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USER_GC")
@Builder
public class UserGC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "First name is mandatory")
    @Size(min = 2, max = 100, message = "First name must be between 2 and 100 characters")
    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_day", nullable = false)
    @Past(message = "Birthday is mandatory")
    private LocalDate birthDay;

    @Enumerated(EnumType.STRING)
    private City city;

    @Enumerated(EnumType.STRING)
    private Country country;

    private String photo;

    private String address;

    private String district;

    private String zipCode;

    @Enumerated(EnumType.STRING)
    private Country currentCountry;

    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "^[+]?[0-9]{6,15}$", message = "Phone number is not valid")
    private String phone;

    @Enumerated(EnumType.STRING)
    private EducationStatus educationStatus;

    @Enumerated(EnumType.STRING)
    private MartialStatus martialStatus;

    private Integer numberOfChildren;
}
