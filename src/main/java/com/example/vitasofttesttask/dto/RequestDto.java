package com.example.vitasofttesttask.dto;

import com.example.vitasofttesttask.entity.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
    private String message;

    public static RequestDto fromRequest(Request request){
        return new RequestDto(request.getMessage());
    }

    public static Request toRequest(RequestDto requestDto) {
        Request request = new Request();
        request.setMessage(requestDto.getMessage());
        return request;
    }
}
