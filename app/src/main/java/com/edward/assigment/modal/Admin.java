package com.edward.assigment.modal;

public class Admin {
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public int get_role() {
        return _role;
    }

    public void set_role(int _role) {
        this._role = _role;
    }

    private String _id;
    private String _username;
    private String _password;
    private int _role;

    public Admin(String _id, String _username, String _password, int _role) {
        this._id = _id;
        this._username = _username;
        this._password = _password;
        this._role = _role;
    }
}
