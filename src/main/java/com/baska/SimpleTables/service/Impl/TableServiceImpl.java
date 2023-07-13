package com.baska.SimpleTables.service.Impl;

import com.baska.SimpleTables.dto.table.*;
import com.baska.SimpleTables.model.Parameters;
import com.baska.SimpleTables.model.Querys;
import com.baska.SimpleTables.model.Tables;
import com.baska.SimpleTables.model.Type;
import com.baska.SimpleTables.repository.Impl.TableRepositoryImpl;
import com.baska.SimpleTables.repository.ParametersRepository;
import com.baska.SimpleTables.repository.QuerysRepository;
import com.baska.SimpleTables.repository.TablesReposotiry;
import com.baska.SimpleTables.repository.TypeRepository;
import com.baska.SimpleTables.service.TableService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {

    private final TablesReposotiry tablesReposotiry;
    private final TableRepositoryImpl tableReposotiry;
    private final TypeRepository typeRepository;
    private final ParametersRepository parametersRepository;
    private final QuerysRepository querysRepository;

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
    public void addData(RowDataRequest jsonData) {
        Long longId = jsonData.getId();
        Tables table = tablesReposotiry.findById(longId).orElseThrow();
        Set<Type> columns = table.getColumns();

        Map<String,Object> data = jsonData.getData();

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

    @Override
    public void addQuery(QueryRequest request) {
        Long longId = request.getTableId();
        Tables table = tablesReposotiry.findById(longId).orElseThrow();
        Set<Querys> querysSet = table.getQuerys();
        Querys querys = new Querys();
        querys.setName(request.getName());
        querys.setQuery(request.getQuery());
        Set<Parameters> parameters = new HashSet<>();
        Set<Type> columns = table.getColumns();
        for(ParametersRequest parameterRequest: request.getParameters()){
            Parameters parameter = new Parameters();
            parameter.setName(parameterRequest.getName());
            for(Type type: columns){
                if (type.getId()==parameterRequest.getTypeId()){
                    parameter.setType(type);
                    break;
                }
            }
            parameter = parametersRepository.save(parameter);
            parameters.add(parameter);
        }
        querys.setParameters(parameters);
        querys = querysRepository.save(querys);
        querysSet.add(querys);
        table.setQuerys(querysSet);
        tablesReposotiry.save(table);
    }

    @Override
    public List<Map<String,Object>> execQuery(ExecuteQueryRequest request) {
        Querys querys = querysRepository.getById(request.getId());

        List<Map<String,Object>> obj =  tableReposotiry.execQuery(querys,request);
        return obj;
    }

}
