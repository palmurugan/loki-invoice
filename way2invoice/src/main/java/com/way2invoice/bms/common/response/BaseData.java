package com.way2invoice.bms.common.response;

/**
 * A base response class
 */
public class BaseData {

    private String key;

    private Object value;

    public BaseData(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BaseData{" +
            "key='" + key + '\'' +
            ", value=" + value +
            '}';
    }
}
