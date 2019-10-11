package com.example.vastujsonparsing;

public class Model {
    String id,table_name,data_masters,value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getData_masters() {
        return data_masters;
    }

    public void setData_masters(String data_masters) {
        this.data_masters = data_masters;
    }
}
