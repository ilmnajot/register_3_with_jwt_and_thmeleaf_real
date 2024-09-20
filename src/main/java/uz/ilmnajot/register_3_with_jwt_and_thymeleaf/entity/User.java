package uz.ilmnajot.register_3_with_jwt_and_thymeleaf.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.enums.Authority;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS")
@Entity
@Setter
@Getter
@ToString
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    @ManyToOne
    private Role role;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Authority> authorities = role.getAuthorities();
       return authorities
               .stream()
               .map(authority -> new SimpleGrantedAuthority(authority.name()))
               .toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
