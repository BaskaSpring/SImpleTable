package com.baska.SimpleTables.dto.table;

import com.baska.SimpleTables.model.PrimitiveType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTableType {

    PrimitiveType type;

    String parametersType;

    String name;

    Boolean otherTable;

    Long tableId;

}
