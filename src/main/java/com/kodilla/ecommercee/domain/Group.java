package com.kodilla.ecommercee.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Group {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy="group")
    private List<Product> products;
}
