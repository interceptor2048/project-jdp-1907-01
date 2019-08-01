package com.kodilla.ecommercee.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "\"USERS\"")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String status;
    private Long userKey;

    public User(String username, String status, Long userKey) {
        this.username = username;
        this.status = status;
        this.userKey = userKey;
    }
}
