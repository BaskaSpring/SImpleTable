package com.baska.SimpleTables.controller;

import com.baska.SimpleTables.dto.table.CreateRequest;
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

    @PostMapping("/table/{id}")
    public ResponseEntity<?> addData(@PathVariable Integer id, @RequestBody String jsonData) {
        tableServiceImpl.addData(id,jsonData);
        return ResponseEntity.ok("");
    }
}
