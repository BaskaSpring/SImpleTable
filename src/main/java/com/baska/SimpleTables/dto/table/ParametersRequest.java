package com.baska.SimpleTables.dto.table;


import com.baska.SimpleTables.model.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParametersRequest {

    String name;

    Long typeId;

}
