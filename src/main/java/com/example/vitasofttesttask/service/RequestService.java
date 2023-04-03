package com.example.vitasofttesttask.service;

import com.example.vitasofttesttask.dto.RequestDto;
import com.example.vitasofttesttask.entity.Request;
import org.springframework.data.domain.Page;

public interface RequestService {

    void userSaveRequests(RequestDto requestDto);

    Page<Request> userFindRequests(Integer pageNumber, boolean fromNewToOld);

    boolean userUpdateRequest(RequestDto requestDto, long id);

    boolean userChangeRequestStatusToSent(long id);

    Page<Request> operatorFindRequests(Integer pageNumber, boolean fromNewToOld, String partOfName);

    boolean operatorChangeRequestStatus(long id, boolean received);
}
