package com.baska.SimpleTables.repository;

import com.baska.SimpleTables.model.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TablesReposotiry extends JpaRepository<Tables,Long> {

    boolean existsByName(String Name);

    boolean existsById(Long Id);

    @Query("select t.name from Tables as t where t.id=:id")
    String takeNameById(Long id);




}
