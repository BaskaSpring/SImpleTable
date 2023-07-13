package com.baska.SimpleTables.dto.table;

import com.baska.SimpleTables.model.Parameters;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryRequest {

    Long tableId;
    String name;
    String query;
    List<ParametersRequest> parameters;

}
