package com.ops.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OpsErrorCode {

    NEWS_EXIST(HttpStatus.CONFLICT, "News exist", "News with id %s already exist"),
    GROUP_NOT_EXIST(HttpStatus.NOT_FOUND, "Group not exist", "Group with name %s does not exist"),
    CATEGORY_ALREADY_EXIST(HttpStatus.CONFLICT, "Category exist", "Category with name %s already exist"),
    PRODUCT_ALREADY_EXIST(HttpStatus.CONFLICT, "Product exist", "Product with name %s already exist"),
    BRAND_ALREADY_EXIST(HttpStatus.CONFLICT, "Brand exist", "Brand with name %s already exist");

    private HttpStatus httpStatus;

    private String message;

    private String description;

    public String getDescription(Object... params) {
        if (params != null) {
            return String.format(description, params);
        }
        return description;
    }

}
