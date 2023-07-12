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
@Table(	name = "types",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
public class Type {

    @Id
    @GeneratedValue
    private long id;

    @Enumerated(EnumType.STRING)
    private PrimitiveType primitiveType;

    private String name;

    private Boolean otherTable;

    private Long tableId;
}
