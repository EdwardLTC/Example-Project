package com.edward.assigment.modal;

public class Category {
    public Category(int _id, String _name) {
        this._id = _id;
        this._name = _name;
    }

    private int _id;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public Category(String _name) {
        this._name = _name;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    private String _name;
}
