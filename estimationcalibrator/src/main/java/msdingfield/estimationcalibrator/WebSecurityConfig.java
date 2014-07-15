package msdingfield.estimationcalibrator;

import java.util.Collections;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private UserDetailsManager userDetailsManager;
	
	@Autowired
	private DataSource dataSource;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        http
            .authorizeRequests()
                .antMatchers("/login", "/signup").permitAll()
                .anyRequest().authenticated();
        http
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	userDetailsManager = auth.jdbcAuthentication().dataSource(dataSource).getUserDetailsService();
    	//auth.jdbcAuthentication().w
    }

	private void inMemoryAuth(AuthenticationManagerBuilder auth)
			throws Exception {
		InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemoryAuthentication = auth
            .inMemoryAuthentication();
        userDetailsManager = inMemoryAuthentication.getUserDetailsService(); 
		inMemoryAuthentication
                .withUser("Ada").password("Quinn").roles("USER");
	}
    
    public boolean addUser(Credentials cred) { 
    	if (!userDetailsManager.userExists(cred.getUsername())) {
	    	UserDetails user = new User(cred.getUsername(), cred.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
	    	userDetailsManager.createUser(user);
			return true;
    	} else {
    		return false;
    	}
    }

}