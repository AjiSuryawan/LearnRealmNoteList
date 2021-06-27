package com.nguliktime.learnrealm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class NoteModel extends RealmObject {

    @PrimaryKey
    private Integer id;
    private String name;
    private String detailnote;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDetailnote(String detailnote) {
        this.detailnote = detailnote;
    }

    public String getDetailnote() {
        return detailnote;
    }
}
