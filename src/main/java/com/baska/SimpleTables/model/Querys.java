package com.baska.SimpleTables.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "querys")
public class Querys {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String query;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "querys_parameters",
            joinColumns = @JoinColumn(name = "query_id"),
            inverseJoinColumns = @JoinColumn(name = "parameter_id")
    )
    private Set<Parameters> parameters;

}
