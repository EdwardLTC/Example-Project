package com.edward.assigment.modal;

public class Order {
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_bookId() {
        return _bookId;
    }

    public void set_bookId(String _bookId) {
        this._bookId = _bookId;
    }

    public String get_adminId() {
        return _adminId;
    }

    public void set_adminId(String _adminId) {
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

    private String _id;
    private String _bookId;
    private String _adminId;
    private String _userId;
    private String dateCreate;
    private String dateReturn;
    private int _status;

    public Order(String _id, String _bookId, String _adminId, String _userId, String dateCreate, String dateReturn, int _status) {
        this._id = _id;
        this._bookId = _bookId;
        this._adminId = _adminId;
        this._userId = _userId;
        this.dateCreate = dateCreate;
        this.dateReturn = dateReturn;
        this._status = _status;
    }


}
