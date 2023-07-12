package com.baska.SimpleTables.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "querys")
public class Parameters {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "query_id", referencedColumnName = "id")
    private Type type;
}
