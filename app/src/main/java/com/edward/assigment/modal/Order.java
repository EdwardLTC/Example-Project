package com.edward.assigment.modal;

public class Order {

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_bookId() {
        return _bookId;
    }

    public void set_bookId(int _bookId) {
        this._bookId = _bookId;
    }

    public int get_adminId() {
        return _adminId;
    }

    public void set_adminId(int _adminId) {
        this._adminId = _adminId;
    }

    public String get_userId() {
        return _userId;
    }

    public void set_userId(String _userId) {
        this._userId = _userId;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
    }

    public int get_status() {
        return _status;
    }

    public void set_status(int _checked) {
        this._status = _checked;
    }

    private int _id;
    private int _bookId;
    private int _adminId;
    private String _userId;
    private String dateCreate;
    private String dateReturn;
    private int _status;

    public Order(int _id, int _bookId, int _adminId, String _userId, String dateCreate, String dateReturn, int _status) {
        this._id = _id;
        this._bookId = _bookId;
        this._adminId = _adminId;
        this._userId = _userId;
        this.dateCreate = dateCreate;
        this.dateReturn = dateReturn;
        this._status = _status;
    }
    public Order(int _bookId, int _adminId, String _userId, String dateCreate, String dateReturn, int _status) {
        this._bookId = _bookId;
        this._adminId = _adminId;
        this._userId = _userId;
        this.dateCreate = dateCreate;
        this.dateReturn = dateReturn;
        this._status = _status;
    }

}
