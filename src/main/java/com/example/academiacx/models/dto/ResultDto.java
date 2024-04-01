package com.example.academiacx.models.dto;

public class ResultDto {

    private String status;
    private String message;
    private Object data;

    public ResultDto(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static ResultDto success(String message, Object data) {
        return new ResultDto("success", message, data);
    }

    public static ResultDto error(String message) {
        return new ResultDto("error", message, null);
    }

    public ResultDto() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultDto{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
