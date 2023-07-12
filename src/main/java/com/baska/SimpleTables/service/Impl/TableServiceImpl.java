package com.baska.SimpleTables.service.Impl;

import com.baska.SimpleTables.dto.table.CreateRequest;
import com.baska.SimpleTables.dto.table.CreateTableType;
import com.baska.SimpleTables.model.Tables;
import com.baska.SimpleTables.model.Type;
import com.baska.SimpleTables.repository.Impl.TableRepositoryImpl;
import com.baska.SimpleTables.repository.TablesReposotiry;
import com.baska.SimpleTables.repository.TypeRepository;
import com.baska.SimpleTables.service.TableService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {

    private final TablesReposotiry tablesReposotiry;
    private final TableRepositoryImpl tableReposotiry;
    private final TypeRepository typeRepository;

    @Override
    @SneakyThrows
    @Transactional
    public void createNewTable(CreateRequest request) {
        boolean failure = false;

        Set<Type> typeList = new HashSet<>();
        //проверка имени Таблицы
        if (!tablesReposotiry.existsByName(request.getName())) {

            Set<CreateTableType> columns = request.getColumns();
            Set<String> names = new HashSet<>();
            for (CreateTableType tableType : columns) {
                //проверка уникальности имени колонок
                int  k = names.size();
                names.add(tableType.getName());

                if (k!=names.size()) {

                    Type type = new Type();
                    type.setPrimitiveType(tableType.getType());
                    type.setOtherTable(tableType.getOtherTable());
                    type.setName(tableType.getName());
                    type.setTableId(tableType.getTableId());

                    Type newType = typeRepository.save(type);

                    typeList.add(newType);

                    //проверка наличия ссылок на таблицы колонок
                    if (tableType.getOtherTable()) {
                        if (!tablesReposotiry.existsById(tableType.getTableId())){
                            failure = true;
                            break;
                        }
                    }
                } else {
                    failure = true;
                    break;
                }
            }
        } else{
            failure = true;
        }

        if (!failure){
            Tables tables = new Tables();
            tables.setName(request.getName());
            tables.setTableView(request.getTableView());
            tables.setColumns(typeList);
            tablesReposotiry.save(tables);

            tableReposotiry.create(request);
        } else {
            throw new Exception("Отмена");
        }

    }


    @Override
    @SneakyThrows
    @Transactional
    public void addData(Integer id,String jsonData) {
        Long longId = id.longValue();
        Tables table = tablesReposotiry.findById(longId).orElseThrow();
        Set<Type> columns = table.getColumns();

        HashMap<String,Object> data = new ObjectMapper().readValue(jsonData, HashMap.class);

        for (Type column: columns){

            String name = column.getName();

            if (!data.containsKey(name)){
                throw new Exception("нету колонки");
            }
            if (column.getOtherTable()){
                Long tableId = column.getTableId();
                //tableReposotiry.findDataTable()
            }
        }
        tableReposotiry.saveData(data,table);


    }


}
