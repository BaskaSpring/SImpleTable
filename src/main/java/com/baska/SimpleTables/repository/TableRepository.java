package com.baska.SimpleTables.repository;

import com.baska.SimpleTables.dto.table.CreateRequest;
import com.baska.SimpleTables.model.Tables;

import java.util.HashMap;

public interface TableRepository {

    void create(CreateRequest request);

    void saveData(HashMap<String,Object> data, Tables table);

    Boolean findDataTable(Long tableId, int postId);
}
