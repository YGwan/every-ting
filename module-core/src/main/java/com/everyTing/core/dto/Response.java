package com.everyTing.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@JsonInclude(Include.NON_NULL)
public class Response<T> {

    private String errorCode;
    private String message;
    private Meta meta;
    private T data;

    private Response(T data) {
        this.data = data;
    }

    private Response(Meta meta, T data) {
        this.meta = meta;
        this.data = data;
    }

    private Response(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    private Response(String errorCode, String message, Meta meta, T data) {
        this.errorCode = errorCode;
        this.message = message;
        this.meta = meta;
        this.data = data;
    }

    public static <T> Response<T> error(String errorCode, String message) {
        return new Response<>(errorCode, message);
    }

    public static <T> Response<T> success(T data) {
        if (data instanceof Page<?>) {
            Page<?> page = (Page<?>) data;

            Meta meta = new Meta(page.getPageable()
                                     .getPageNumber(), page.getSize(), page.getTotalElements(),
                page.getTotalPages());
            return new Response<>(meta, (T) page.getContent());
        }

        return new Response<>(data);
    }

    public static <T> ResponseEntity<T> createSuccess(T data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }
    public static <T> Response<T> error() {
        return null;
    }

    public static <T> Response<T> success() {
        return null;
    }

    public static class Meta {

        private int page;
        private int size;
        private long totalElements;
        private int totalPages;

        public Meta(int page, int size, long totalElements, int totalPages) {
            this.page = page;
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
        }
    }
}
