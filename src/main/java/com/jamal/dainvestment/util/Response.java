package com.jamal.dainvestment.util;

import lombok.Data;

/**
 * This is a Javadoc comment
 * Response message
 * @param <T> for data
 */
@Data
public class Response<T> {
    private String service;
    private String message;
    private T data;
}
