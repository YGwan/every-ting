package com.everyTing.core.config;

import static java.util.stream.Collectors.groupingBy;

import com.everyTing.core.dto.Response;
import com.everyTing.core.exception.errorCode.ApplicationErrorCode;
import com.everyTing.core.swagger.ApiErrorCode;
import com.everyTing.core.swagger.SwaggerErrorCode;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

@Configuration
public class SwaggerErrorCodeConfig {

    @Bean
    public OperationCustomizer customize() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            ApiErrorCode apiErrorCode =
                handlerMethod.getMethodAnnotation(ApiErrorCode.class);
            if (apiErrorCode != null) {
                generateErrorCodeResponse(operation, apiErrorCode.values());
            }
            return operation;
        };
    }

    private void generateErrorCodeResponse(
        Operation operation, Class<? extends SwaggerErrorCode>[] types) {

        List<ApplicationErrorCode> allErrorCodes = Arrays.stream(types)
                                                         .flatMap(type -> Arrays.stream(
                                                             type.getEnumConstants()))
                                                         .map(SwaggerErrorCode::getErrorCode)
                                                         .collect(Collectors.toUnmodifiableList());

        Map<Integer, List<ExampleHolder>> statusWithExampleHolders =
            allErrorCodes.stream()
                         .map(errorCode ->
                             ExampleHolder.builder()
                                          .holder(getSwaggerExample(errorCode))
                                          .code(errorCode.getStatus()
                                                         .value())
                                          .name(errorCode.getErrorCode())
                                          .build())
                         .collect(groupingBy(ExampleHolder::getCode));

        addExamplesToResponses(operation.getResponses(), statusWithExampleHolders);
    }

    private Example getSwaggerExample(ApplicationErrorCode errorCode) {
        Response response = Response.error(errorCode.getErrorCode(), errorCode.getMessage());
        Example example = new Example();
        example.setValue(response);
        return example;
    }

    private void addExamplesToResponses(
        ApiResponses responses, Map<Integer, List<ExampleHolder>> statusWithExampleHolders) {
        statusWithExampleHolders.forEach(
            (status, v) -> {
                Content content = new Content();
                MediaType mediaType = new MediaType();
                ApiResponse apiResponse = new ApiResponse();
                v.forEach(
                    exampleHolder -> mediaType.addExamples(
                        exampleHolder.getName(), exampleHolder.getHolder()));
                content.addMediaType("application/json", mediaType);
                apiResponse.setContent(content);
                responses.addApiResponse(status.toString(), apiResponse);
            });
    }

    @Getter
    @Builder
    public static class ExampleHolder {
        private Example holder;
        private String name;
        private int code;
    }
}
