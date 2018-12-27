package com.oims.futureprogram.controller;

import com.oims.futureprogram.model.Response;

public abstract class GlobalController {
    public <T> Response toResponse(T value) {
        return Response.builder().code("200").message("Success").data(value).build();
    }
}
