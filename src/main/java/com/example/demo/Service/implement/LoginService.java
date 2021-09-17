package com.example.demo.Service.implement;

import com.example.demo.Entity.PerSon;
import com.example.demo.PerSonDto.PersonDTO;
import com.example.demo.Repository.PerSonUser;
import com.example.demo.Repository.PersonRepo;
import com.example.demo.Service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LoginService implements UserSevice {

    @Autowired
    PersonRepo personRepo;
@Autowired
    PerSonUser perSonUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PerSon p =perSonUser.getByUsername(username);
        System.out.println(p);
        if(p == null){
            throw new UsernameNotFoundException("not found");
        }
//convert role sang security
        String role = p.getRole();

        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(role));

        //convert user model cua Security
        User currentUser  = new User(username,p.getPassWord(),authorities);

        return currentUser;
    }

    @Override
    public void save(PersonDTO personDTO) {
        PerSon p1 = new PerSon();
        p1.setId(personDTO.getId());
        p1.setName(personDTO.getName());
        p1.setUsername(personDTO.getUsername());
        p1.setEmail(personDTO.getEmail());
        p1.setPhone(personDTO.getPhone());
        p1.setPassWord(new BCryptPasswordEncoder().encode(personDTO.getPassWord()));
        p1.setRole("ROLE_ADMIN");
        personRepo.save(p1);

    }
}
