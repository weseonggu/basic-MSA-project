package com.spring_cloud.eureka.client.order.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class HttpClientRequestException extends RuntimeException {
    public HttpClientRequestException(String message) {
        super(message);
    }
}
