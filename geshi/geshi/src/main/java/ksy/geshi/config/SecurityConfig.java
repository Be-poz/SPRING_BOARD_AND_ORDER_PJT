package ksy.geshi.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/css/**","/js/**","/img/**","/lib/**");
    }

    @Override
    protected  void configure(HttpSecurity http) throws Exception{
        http
                .csrf()
                    .disable()
                    .authorizeRequests()
                        .antMatchers("/board/main",
                                "/",
                                "/user/login",
                                "/user/register")
                                .permitAll()
                        .antMatchers("/item/**")
                                .hasRole("ADMIN")
                        .anyRequest()
                        .authenticated()
                .and()
                .formLogin()
                    .loginPage("/user/login")
                    .successHandler(new MyLoginSuccessHandler())
                    .failureHandler(new MyLoginFailureHandler())
                .and()
                .logout()
                    .logoutUrl("/user/logout")
                    .logoutSuccessUrl("/user/login")
                .and()
                .exceptionHandling().accessDeniedPage("/user_denied");
    }

}
