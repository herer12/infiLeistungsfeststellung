package szu.infi.factory;

import szu.infi.domain.IDataLayer;

public class DBInstance {

    public static IDataLayer getInstance(String dbType) {

        switch(dbType) {
            case "MySQL":
                return new szu.infi.mysql.DataLayer();
            case "SQLite":
                return new szu.infi.sqlite.DataLayer();
            case "JSON":
                return new szu.infi.json.DataLayer();
            default:
                return null;
        }
    }
}
