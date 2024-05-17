package com.gaoge.exception;

/**
 * 上传文件格式（后缀名）异常
 */
public class FileFormatException extends Exception{
    public FileFormatException() {
    }

    public FileFormatException(String message) {
        super(message);
    }
}
