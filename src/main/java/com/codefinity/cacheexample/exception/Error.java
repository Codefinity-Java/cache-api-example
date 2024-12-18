package com.codefinity.cacheexample.exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Error {
    private String errorMessage;
}
