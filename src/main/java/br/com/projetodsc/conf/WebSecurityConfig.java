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
		.antMatchers("/resources/**", "/css/**", "/app/**", "/js/**", "/images/**", "/assets/**", "/fonts/**").permitAll()
		.antMatchers("/fragmentos/**").permitAll()
		.antMatchers("/autor/**").hasAnyAuthority("ADMINISTRADOR")
		.antMatchers("/categoria/**").hasAnyAuthority("ADMINISTRADOR")
		.antMatchers("/compra/**").permitAll()
		.antMatchers("/editora/**").hasAnyAuthority("ADMINISTRADOR")
		.antMatchers("/livro/**").permitAll()
		.antMatchers("/pedido/**").permitAll()
		.antMatchers("/promocao/**").hasAnyAuthority("ADMINISTRADOR")
		.antMatchers("/email/**").hasAnyAuthority("ADMINISTRADOR")
		.antMatchers("/usuario/**").permitAll()
		/* Métodos GET */
		.antMatchers(HttpMethod.GET, "/").permitAll()
		.antMatchers(HttpMethod.GET, "/livro/listaAll/**").permitAll()
		.antMatchers(HttpMethod.GET, "/livro/buscaValoresIntervalors/**").permitAll()
		.antMatchers(HttpMethod.GET, "/pedido/pedidos/**").permitAll()
		.antMatchers(HttpMethod.GET, "/usuario/mydados/**").hasRole("CLIENTE")
		.antMatchers(HttpMethod.GET, "/pedido/myPedidos/**").hasRole("CLIENTE")
		.antMatchers(HttpMethod.GET, "/usuario/listaComprasUsuario/**").hasRole("CLIENTE")
		.antMatchers(HttpMethod.GET, "/livrojson/livros/**").permitAll()
		.antMatchers(HttpMethod.GET, "/livro/detalheLivro/**").permitAll()
		.antMatchers(HttpMethod.GET, "/pedido/cancelaPedido/**").hasRole("CLIENTE")
		.antMatchers(HttpMethod.GET, "http://api.postmon.com.br/v1/cep/**").permitAll()
		.antMatchers(HttpMethod.GET, "https://api-correios-soap.herokuapp.com/**").permitAll()
		.antMatchers(HttpMethod.GET, "/usuariojson/usuario").permitAll()
		.antMatchers(HttpMethod.GET, "/livro/lista-livros-categoria/**").permitAll()
		.antMatchers(HttpMethod.GET, "/livrojson/livros/finalizar-compra").permitAll()
		/* Métodos POST */
		
		.antMatchers(HttpMethod.POST, "/pedido/savePedido").permitAll()
		.antMatchers(HttpMethod.POST, "/livrojson/livroJaAdd").permitAll()
		.antMatchers(HttpMethod.POST, "/compra/saveCompra").permitAll()
		.antMatchers(HttpMethod.POST, "/usuario/saveUsuario").permitAll()
		.antMatchers(HttpMethod.POST, "/login").permitAll()
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
