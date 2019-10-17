package com.simple.mmall.exception;

/**
 * MmallException
 * 系统异常
 *
 * @author Simple
 * @create 2018-01-18 10:27
 * @since v1.0
 **/
public class MmallException extends RuntimeException {

    private int code;
    private String result;

    protected MmallException() {
        super();
    }

    protected MmallException(int code, String result) {
        super(result);
        this.setCode(code);
        this.setResult(result);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
