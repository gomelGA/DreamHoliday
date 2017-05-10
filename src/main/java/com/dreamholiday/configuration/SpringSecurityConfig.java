package com.dreamholiday.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private OrdinaryUserService ordinaryUserService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(this.ordinaryUserService).passwordEncoder(getBCryptPasswordEncoder());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/register/**", "/bootstrap/**", "/jquery/**", "/css/**", "/images/**", "/connect/**", "/offers/**").permitAll()
                .antMatchers("/offers/all/administrator").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
                .formLogin().loginPage("/login").permitAll()
                .usernameParameter("email").passwordParameter("password")
                .defaultSuccessUrl("/")
            .and()
                .rememberMe()
                .rememberMeParameter("rememberMe")
                .rememberMeCookieName("rememberMe")
                .key("MisteryKey")
                .tokenValiditySeconds(604800)
            .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/unauthorized")
            .and()
                .csrf().csrfTokenRepository(csrfTokenRepository());
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setSessionAttributeName("_csrf");
        return repository;
    }
}
