package com.project.bulbeniback.entity;

import org.hibernate.annotations.DialectOverride.GeneratedColumn;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
   
    String name;
   
    String surname;
   
    String username;
   
    String email;
   
    String password;
   
    String role;
   
    String token; //I havent decided yet if I will use this field


}
