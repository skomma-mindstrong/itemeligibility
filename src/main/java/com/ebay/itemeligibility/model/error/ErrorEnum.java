package com.ebay.itemeligibility.model.error;

import org.springframework.http.HttpStatus;

/**
 * Enums for the error codes and validation used
 */
public enum ErrorEnum {
    SELLER_NOT_FOUND(
            HttpStatus.BAD_REQUEST.value(),
            "Seller Not found"),
    SELLER_ALREADY_ENROLLED(
            HttpStatus.FORBIDDEN.value(),
            "Seller Already enrolled"),
    SELLER_NOT_ENROLLED(
            HttpStatus.FORBIDDEN.value(),
            "Seller is not enrolled"),
    CATEGORY_NOT_FOUND(
            HttpStatus.BAD_REQUEST.value(),
            "Category Not found"),
    CATEGORY_ALREADY_APPROVED(
            HttpStatus.FORBIDDEN.value(),
            "Category Already approved"),
    CATEGORY_NOT_APPROVED(
            HttpStatus.FORBIDDEN.value(),
            "Category is not approved"),
    INTERNAL_ERROR(
            HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
    CATEGORY_DUPLICATE(
            HttpStatus.FORBIDDEN.value(),
            "Category already exists ,Duplicates cannot be added"),
    ITEM_DUPLICATE(
            HttpStatus.FORBIDDEN.value(),
            "Item already exists ,Duplicates cannot be added"),
    BAD_REQUEST_ADD_ITEM(
            HttpStatus.BAD_REQUEST.value(),
            "Request parameters are not complete ,Please check for all the mandatory attributes"),
    BAD_REQUEST_ELIGIBILITY_CHECK_CATEGORY(
            HttpStatus.BAD_REQUEST.value(),
            "Invalid Category input :No category exists for the input"),
    BAD_REQUEST_ELIGIBILITY_CHECK_SELLER(
            HttpStatus.BAD_REQUEST.value(),
            "Invalid Seller input :No Seller Exists for the input"),
    BAD_REQUEST_ELIGIBILITY_CHECK_PRICE(
            HttpStatus.BAD_REQUEST.value(),
            "Invalid Category input :Price of the item is not the same as saved"),
    SELLER_DUPLICATE(
            HttpStatus.FORBIDDEN.value(),
            "Seller already exists ,Duplicates cannot be added");



    private final int code;
    private final String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private ErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}