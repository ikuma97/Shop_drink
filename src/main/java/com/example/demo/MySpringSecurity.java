package com.example.demo;




import com.example.demo.Service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class MySpringSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    UserSevice userDetailsService;

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //phan quyen
        http.csrf().disable().authorizeRequests().antMatchers("/admin**").hasAnyRole("ADMIN")
                .antMatchers("/member/**").hasAnyRole("MEMBER").anyRequest().permitAll()

                //cau hinh giao dien
                .and().formLogin().loginPage("/dang-nhap").loginProcessingUrl("/login")
                .and().exceptionHandling().accessDeniedPage("/acess-deny");
    }

//@Autowired
//public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//    auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
//            .withUser("admin")
//            .password(new BCryptPasswordEncoder().encode("123"))
//            .roles("ADMIN");
//}

    //    }
//@Bean
//@Override
//public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//
//
//    }

//    public static void main(String[] args) {
//        String rawPass = "123456";
//        String encode = new BCryptPasswordEncoder().encode(rawPass);
//        System.out.println(encode);
//        boolean check = new BCryptPasswordEncoder().matches(rawPass,encode);
//        System.out.println(check);
//    }

    //$2a$10$MhsYKPapXEFeZIXBITcpFuMdPx./GAJp51Zgekikq8Gf1Y6S4h1ni
    //authenticationProvider bean definition
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
//        auth.setUserDetailsService((UserDetailsService) userDetais); //set the custom user details service
//        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
//        return auth;
//    }
}
