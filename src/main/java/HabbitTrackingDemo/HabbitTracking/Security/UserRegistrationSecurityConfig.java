package HabbitTrackingDemo.HabbitTracking.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import HabbitTrackingDemo.HabbitTracking.enumclasses.Role;

@Configuration
@EnableWebSecurity
public class UserRegistrationSecurityConfig {


//  @Bean
//  public PasswordEncoder passwordEncoder() {
//      return new BCryptPasswordEncoder();
//  }
//this is my previous code i comment this becouse it
//creating problem in login the problem is it showing an
//error in postman the error is 403 Forbidden
//  Access to the resource is prohibited.
  
//  @Bean
//  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//      return http.cors().and().csrf().disable()
//          .authorizeHttpRequests()
//          .requestMatchers("/register/**").permitAll()  // Public endpoints
//          .requestMatchers("/users/**").hasAnyAuthority("USER", "ADMIN")  // Role-based endpoints
//          .anyRequest().authenticated()  // Restrict all other endpoints
//          .and()
//          .build();
//
//  }
  

//  @Bean
//  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//      http.cors().and()
//          .csrf().disable()  // Disable CSRF for testing (you can enable it in production)
//          .authorizeRequests()
//          .requestMatchers("/**").permitAll()  // Allow all endpoints for testing
//          .anyRequest().authenticated();  // Require authentication for other endpoints
//
//      return http.build();
//  } 
  
	
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Using BCryptPasswordEncoder for production
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors().and()
                .csrf().disable() // Disable CSRF for testing (you can re-enable it in production)
                .authorizeHttpRequests()
                .requestMatchers("/register/**", "/users/login","/users/habit","/users/habit/getbycategory",
                		"/users/habit/habitname").permitAll() 
                // Public endpoints
                 .anyRequest().authenticated()  // Restrict all other endpoints
                .and()
                .build();
    }
    
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.cors().and()
//                .csrf().disable()
//                .authorizeHttpRequests()
//                .anyRequest().permitAll()
//                .and()
//                .build(); 
//    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
            User.withUsername("user")
                .password(passwordEncoder().encode("password"))
                .authorities(Role.ROLE_USER.name())
                .disabled(false)  // User is enabled and can log in
                .build(),
            
            User.withUsername("admin")
                .password(passwordEncoder().encode("password"))
                .authorities(Role.ROLE_ADMIN.name())
                .disabled(false)  // User is disabled and cannot log in
                .build()
        );
    }
    
    
    
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder())  // Using BCryptPasswordEncoder
                .and()
                .build();
    }
}
