package com.sda.springhrapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity()
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projectId")
    private Integer id;
    @Column(name = "projectName")
    private String name;
    @Transient
    private double budget;
    @Transient
    private String currency;
    @Enumerated(EnumType.STRING)
    @Column(name="type")
    private ProjectType projectType;

    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees= new HashSet<>();


}
