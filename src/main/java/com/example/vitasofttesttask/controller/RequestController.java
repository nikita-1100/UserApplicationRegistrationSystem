package com.example.vitasofttesttask.controller;


import com.example.vitasofttesttask.dto.RequestDto;
import com.example.vitasofttesttask.entity.Request;
import com.example.vitasofttesttask.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @PutMapping("user/requests/{id}")
    public HttpStatus update(@PathVariable("id") long id, @RequestBody RequestDto requestDto){
        final boolean updated = requestService.userUpdateRequest(requestDto, id);
        return updated ? HttpStatus.OK:HttpStatus.NOT_MODIFIED;
    }

    @PostMapping("user/requests/{id}")
    public HttpStatus userUpdateStatusToSent(@PathVariable("id") long id){
        final boolean updated = requestService.userChangeRequestStatusToSent(id);
        return updated ? HttpStatus.OK:HttpStatus.NOT_MODIFIED;
    }

    @PostMapping("user/requests")
    public HttpStatus newRequest(@RequestBody RequestDto requestDto){
        requestService.userSaveRequests(requestDto);
        return HttpStatus.CREATED;
    }

    @GetMapping("user/requests")
    public Page<Request> findByUser(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "fromOldToNew", required = false, defaultValue = "true") boolean fromOldToNew){
        return requestService.userFindRequests(pageNumber, fromOldToNew);
    }

    @GetMapping("operator/requests")
    public Page<Request> findSentRequests(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "fromOldToNew", required = false, defaultValue = "true") boolean fromOldToNew,
            @RequestParam(value = "partOfName", required = false, defaultValue = "") String partOfName){
        return requestService.operatorFindRequests(pageNumber, fromOldToNew, partOfName);
    }

    @PostMapping("operator/requests/{id}")
    public HttpStatus operatorUpdateStatus(@PathVariable("id") long id,
                                           @RequestParam(value = "received") boolean received){
        final boolean updated = requestService.operatorChangeRequestStatus(id, received);
        return updated ? HttpStatus.OK:HttpStatus.NOT_MODIFIED;
    }
}
