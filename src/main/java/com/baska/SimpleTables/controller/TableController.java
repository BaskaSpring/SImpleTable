package com.baska.SimpleTables.controller;

import com.baska.SimpleTables.dto.table.CreateRequest;
import com.baska.SimpleTables.dto.table.ExecuteQueryRequest;
import com.baska.SimpleTables.dto.table.QueryRequest;
import com.baska.SimpleTables.dto.table.RowDataRequest;
import com.baska.SimpleTables.service.Impl.TableServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/table")
@RequiredArgsConstructor
public class TableController {

    private final TableServiceImpl tableServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<?> createTable(@RequestBody CreateRequest request) {
        tableServiceImpl.createNewTable(request);
        return ResponseEntity.ok("");
    }

    @PostMapping("/row")
    public ResponseEntity<?> addData(@RequestBody RowDataRequest data) {
        tableServiceImpl.addData(data);
        return ResponseEntity.ok("");
    }

    @PostMapping("/query")
    public ResponseEntity<?> addQuery(@RequestBody QueryRequest request) {
        tableServiceImpl.addQuery(request);
        return ResponseEntity.ok("");
    }

    @GetMapping("/query")
    public ResponseEntity<?> execQuery(@RequestBody ExecuteQueryRequest queryRequest) {
        return ResponseEntity.ok(tableServiceImpl.execQuery(queryRequest));
    }

}
