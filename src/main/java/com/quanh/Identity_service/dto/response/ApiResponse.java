package com.quanh.Identity_service.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse <T> {              //Chuẩn hóa cấu trúc JSON cho frontend
    @Builder.Default
    int code = 1000;                //Hoặc có thể setCode ở trong UserController

    String message;
    T result;
}
