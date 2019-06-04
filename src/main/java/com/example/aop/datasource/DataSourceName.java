package com.example.aop.datasource;

/**
 * @author yebing
 */

public enum DataSourceName {
    MASTER("MASTER-DATASOURCE",1),SLAVE("SLAVE-DATASOURCE",2);
    private String name;
    private int index;
    DataSourceName(String name, int index){
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
