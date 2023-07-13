package com.baska.SimpleTables.repository;

import com.baska.SimpleTables.dto.table.CreateRequest;
import com.baska.SimpleTables.dto.table.ExecuteQueryRequest;
import com.baska.SimpleTables.model.Querys;
import com.baska.SimpleTables.model.Tables;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TableRepository {

    void create(CreateRequest request);

    void saveData(Map<String,Object> data, Tables table);

    Boolean findDataTable(Long tableId, int postId);

    List<Map<String,Object>> execQuery(Querys query,ExecuteQueryRequest request);
}
