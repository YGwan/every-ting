package com.everyTing.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@JsonInclude(Include.NON_NULL)
public class Response<T> {

    private String errorCode;
    private String message;
    private Meta meta;
    private T data;

    private Response(String errorCode, String message, Meta meta, T data) {
        this.errorCode = errorCode;
        this.message = message;
        this.meta = meta;
        this.data = data;
    }

    public static <T> Response<T> error(String errorCode, String message) {
        if (errorCode == null) {
            return null;
        }

        return new Response<>(errorCode, message, null, null);
    }

    public static <T> Response<T> success() {
        return null;
    }

    public static <T> Response<T> success(T data) {
        if (data instanceof Page<?>) {
            Page<?> page = (Page<?>) data;

            Meta meta = new Meta(page.getPageable()
                                     .getPageNumber(), page.getSize(), page.getTotalElements(),
                page.getTotalPages());
            return new Response<>(null, null, meta, (T) page.getContent());
        }

        return new Response<>(null, null, null, data);
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
