package com.baska.SimpleTables.dto.table;

import com.baska.SimpleTables.model.TableView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateRequest {

    String name;

    TableView tableView;

    Set<CreateTableType> columns;
}
