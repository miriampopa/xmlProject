package com.library_project.model;

import java.util.List;

public class AjaxResponseBody<T> {

    String msg;
    List<T> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
