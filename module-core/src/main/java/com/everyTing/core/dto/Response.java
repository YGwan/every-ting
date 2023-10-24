package com.everyTing.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import org.springframework.data.domain.Slice;

@Getter
@JsonInclude(Include.NON_NULL)
public class Response<T> {

    private String errorCode;
    private String message;
    private Meta meta;
    private T data;

    protected Response() {
    }

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
        if (data instanceof Slice<?>) {
            Slice<?> slice = (Slice<?>) data;

            Meta meta = new Meta(slice.getPageable().getOffset(), slice.getNumberOfElements(),
                slice.isFirst(), slice.isLast());
            return new Response<>(meta, (T) slice.getContent());
        }

        return new Response<>(data);
    }

    public static <T> Response<T> error() {
        return null;
    }

    public static <T> Response<T> success() {
        return null;
    }

    @Getter
    public static class Meta {

        private long offset;
        private int numOfElements;
        private boolean first;
        private boolean last;

        public Meta(long offset, int numOfElements, boolean first, boolean last) {
            this.offset = offset;
            this.numOfElements = numOfElements;
            this.first = first;
            this.last = last;
        }
    }
}
