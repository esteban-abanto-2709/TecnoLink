package com.tecnolink.tecnolink.shared.interfaces.rest;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiError(String error, List<String> errors) {

    public static ApiError of(String error) {
        return new ApiError(error, null);
    }

    public static ApiError of(List<String> errors) {
        return new ApiError(null, errors);
    }
}
