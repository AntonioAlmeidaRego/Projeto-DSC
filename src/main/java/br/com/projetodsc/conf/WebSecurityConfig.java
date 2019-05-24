package br.com.projetodsc.conf;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailsService service;

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		telas(http);
	}
	
	private void telas(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/resources/**", "/css/**", "/app/**", "/js/**", "/images/**", "/assets/**", "/fonts/**", "/h2/**").permitAll()
		.antMatchers(HttpMethod.GET, "/administrador/portal-admin").hasRole("Admin")
		.antMatchers(HttpMethod.GET, "/").permitAll()
		.antMatchers(HttpMethod.GET, "/livro/listaAll/**").permitAll()
		.antMatchers(HttpMethod.GET, "/livro/buscaValoresIntervalors/**").permitAll()
		.antMatchers(HttpMethod.GET, "/pedido/pedidos/**").permitAll()
		.antMatchers(HttpMethod.POST, "/usuario/saveUsuario").permitAll()
		.anyRequest().authenticated().and().formLogin().loginPage("/entrar").permitAll()
		.successForwardUrl("/usuario/portal-user").and().logout().permitAll()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/entrar");
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
