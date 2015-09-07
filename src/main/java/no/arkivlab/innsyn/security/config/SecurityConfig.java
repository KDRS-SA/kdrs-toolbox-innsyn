package no.arkivlab.innsyn.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
    protected void configure(HttpSecurity http) throws Exception {
		// TODO: temporary so i can use curl. In web solution this would be removed
		http.csrf().disable();
		
		// The following values are just added for testing purposes. These would need to be configured better
		  http.authorizeRequests()
			//.antMatchers("/fonds/**").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/fonds/**").permitAll()
		  	.antMatchers("/fondsCreator/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
			.antMatchers("/series/**").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/classificationSystem/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")			
			.antMatchers("/class/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
			.antMatchers("/file/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
			.antMatchers("/record/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
			.antMatchers("/documentDescription/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
			.antMatchers("/documentObject/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")			
			.antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_ARKIVAR')")
	        .anyRequest().authenticated()
		  .and()
		  
        .formLogin()
        .loginPage("/login")
            .permitAll()
            .and()
        .logout()
            .permitAll();
	     }

	@Autowired
	public void configureGlobal(
			AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("user").password("password").roles("USER").and()
				.withUser("admin").password("password").roles("ADMIN");
	}
}
