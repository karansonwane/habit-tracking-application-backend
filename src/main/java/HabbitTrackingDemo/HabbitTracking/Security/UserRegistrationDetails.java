package HabbitTrackingDemo.HabbitTracking.Security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import HabbitTrackingDemo.HabbitTracking.Model.User;
import lombok.Data;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserRegistrationDetails implements UserDetails {

    private String userName;
    private String password;
    private boolean isEnabled;
    private List<GrantedAuthority> authorities;

    // Constructor that initializes the UserDetails object with user data
    public UserRegistrationDetails(User user) {
        this.userName = user.getEmail();  // Use email as the username
        this.password = user.getPassword();
        this.isEnabled = user.isEnabled();

        // Ensure role is prefixed with 'ROLE_' (e.g., 'ROLE_USER', 'ROLE_ADMIN')
        this.authorities = Arrays.stream(user.getRole().split(","))
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))  // Add 'ROLE_' prefix
                .collect(Collectors.toList());this.authorities = user.getRole() != null ?
                        Arrays.stream(user.getRole().split(","))
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.trim()))
                        .collect(Collectors.toList())
                  : List.of();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;  // Return the list of granted authorities (roles)
    }

    @Override
    public String getPassword() {
        return password;  // Return the password
    }

    @Override
    public String getUsername() {
        return userName;  // Return the username (email)
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Assuming the account never expires
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Assuming the account is never locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Assuming the credentials never expire
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;  // Return if the user is enabled (active)
    }
}
