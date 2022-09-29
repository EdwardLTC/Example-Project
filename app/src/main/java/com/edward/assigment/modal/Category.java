package com.edward.assigment.modal;

public class Category {
    public Category(String _id, String _name) {
        this._id = _id;
        this._name = _name;
    }

    private String _id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    private String _name;
}
