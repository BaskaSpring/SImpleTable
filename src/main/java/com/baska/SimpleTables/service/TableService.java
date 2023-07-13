package com.baska.SimpleTables.service;

import com.baska.SimpleTables.dto.table.CreateRequest;
import com.baska.SimpleTables.dto.table.ExecuteQueryRequest;
import com.baska.SimpleTables.dto.table.QueryRequest;
import com.baska.SimpleTables.dto.table.RowDataRequest;

import java.util.List;
import java.util.Map;

public interface TableService {

    void createNewTable(CreateRequest request);

    void addData(RowDataRequest data);

    void addQuery(QueryRequest request);

    List<Map<String,Object>> execQuery(ExecuteQueryRequest request);
}
