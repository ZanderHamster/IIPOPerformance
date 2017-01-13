package edu.bstu.iipo.a13ivt1.iipoperformance.DataBase;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = UniversityDB.class)
public class TestStudent extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    int id;

    public int getId() {
        return id;
    }

    @Column
    String name;

    @Column
    String surname;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
