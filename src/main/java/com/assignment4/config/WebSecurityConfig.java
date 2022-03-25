/**
 * 
 */
package com.assignment4.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Abhinav
 * Date - 24 Mar, 2022
 * Description - It is used to customize the spring scurity
 *
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	/**
	 * Configuring the URL which needs to be secured and which are public.
	 * Also, defines the customize login page
	 * 
	 */
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/register", "/index**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/index")
                .failureUrl("/login?error=true")
                .and()
            .logout()
                .permitAll();
                //.formLogin().permitAll();
    }

	/**
	 * It checks the username and password entered by the user
	 * 
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, 'true' as enabled from login where username=?")
				.authoritiesByUsernameQuery("SELECT username, 'ROLE_USER' FROM login WHERE username=?")
				.passwordEncoder(new BCryptPasswordEncoder());
	}

}
