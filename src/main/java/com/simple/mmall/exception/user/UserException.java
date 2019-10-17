package com.simple.mmall.exception.user;

import com.simple.mmall.exception.MmallException;

/**
 * UserException
 * 用户异常
 *
 * @author Simple
 * @create 2018-01-18 10:49
 * @since v1.0
 **/
public class UserException extends MmallException {
    public UserException() {
    }

    public UserException(int code, String result) {
        super(code, result);
    }
}
