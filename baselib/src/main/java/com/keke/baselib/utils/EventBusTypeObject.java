package com.keke.baselib.utils;

/**
 * Created by hotyq on 2016/4/25.
 * Since on 2016/4/25
 */
public class EventBusTypeObject<T> {
    private int mMsg;
    private T object;

    public EventBusTypeObject(int msg, T object) {
        mMsg = msg;
        this.object = object;
    }

    public int getMsg() {
        return mMsg;
    }

    public void setMsg(int msg) {
        mMsg = msg;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

}
