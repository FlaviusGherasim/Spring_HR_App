package com.sda.springhrapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "departments") // mandatory - singular vs plural
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column // optional - identical names
    private Integer id;
    @Column // optional - identical names
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("department")
    private List<Employee> employeeList;

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
