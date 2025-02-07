package com.dailycode.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long userId;
private String firstName;
private String lastName;
private String email;
private Long departmentId;
}
