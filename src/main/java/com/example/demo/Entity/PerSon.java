package com.example.demo.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Data
@Setter
@Getter
@Entity
@Table(name = "Person")
public class PerSon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "userName")
    private String username;


    @Column(name = "password")
    private String passWord;

    @Column(name = "role")
    private String role;

    @Column(name = "Enable")
    private String enable;
public PerSon(){

}


}