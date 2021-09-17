package com.example.demo.Service;

import com.example.demo.Entity.PerSon;
import com.example.demo.PerSonDto.PersonDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserSevice extends UserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
     void save(PersonDTO personDTO);
}
