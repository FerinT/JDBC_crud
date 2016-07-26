package main;

import main.CrudOperations;

/**
 * Created by tayfer01 on 7/26/2016.
 */
public class Main {

    public static void main(String[] args) {

        CrudOperations crudOperations = new CrudOperations();

        crudOperations.createTable();
        crudOperations.insertData();
        crudOperations.retrieveData();
        crudOperations.updateRecord("Finance");
        crudOperations.removeRecord();

    }
}
