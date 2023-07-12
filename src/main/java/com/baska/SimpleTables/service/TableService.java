package com.baska.SimpleTables.service;

import com.baska.SimpleTables.dto.table.CreateRequest;

public interface TableService {

    void createNewTable(CreateRequest request);

    void addData(Integer id, String jsonData);
}
