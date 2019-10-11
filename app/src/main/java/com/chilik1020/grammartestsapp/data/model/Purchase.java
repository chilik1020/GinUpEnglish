package com.chilik1020.grammartestsapp.data.model;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "purchases")
public class Purchase implements Serializable {

    @NonNull
    private Long _id;

    @PrimaryKey
    @NonNull
    private String name;

    private int status;

    public Purchase(Long _id, @NonNull String name, int status) {
        this._id = _id;
        this.name = name;
        this.status = status;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
