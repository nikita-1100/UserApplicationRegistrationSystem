package com.example.vitasofttesttask.service;

import com.example.vitasofttesttask.dto.RequestDto;
import com.example.vitasofttesttask.entity.AppUser;
import com.example.vitasofttesttask.entity.Request;
import com.example.vitasofttesttask.entity.RequestStatus;
import com.example.vitasofttesttask.repository.RequestRepository;
import com.example.vitasofttesttask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService{
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    public void userSaveRequests(RequestDto requestDto) {
        Request request = RequestDto.toRequest(requestDto);
        request.setStatus(RequestStatus.DRAFT);
        request.setAuthor(getCurrentUser());
        request.setCreated(new Date());
        requestRepository.save(request);
    }

    public Page<Request> userFindRequests(Integer pageNumber, boolean fromNewToOld) {
        Pageable pageable = PageRequest.of(pageNumber, 5);
        if (fromNewToOld)
            return requestRepository.findByAuthorOrderByCreated(pageable, getCurrentUser());
        else
            return requestRepository.findByAuthorOrderByCreatedDesc(pageable, getCurrentUser());
    }

    public boolean userUpdateRequest(RequestDto requestDto, long id) {
        Request request = requestRepository.findById(id).orElse(null);
        if (request!=null
                && request.getAuthor().getUsername().equals(getCurrentUser().getUsername())
                && request.getStatus()==RequestStatus.DRAFT){
            request.setMessage(requestDto.getMessage());
            requestRepository.save(request);
            return true;
        } else
            return false;
    }

    public boolean userChangeRequestStatusToSent(long id) {
        Request request = requestRepository.findById(id).orElse(null);
        if (request!=null
                && request.getAuthor().getUsername().equals(getCurrentUser().getUsername())
                && request.getStatus()==RequestStatus.DRAFT){
            request.setStatus(RequestStatus.SENT);
            requestRepository.save(request);
            return true;
        } else
            return false;
    }

    public Page<Request> operatorFindRequests(Integer pageNumber, boolean fromNewToOld, String partOfName) {
        Page<Request> requestPages;
        Pageable pageable = PageRequest.of(pageNumber, 5);
        if (partOfName.length() > 0) {
            List<AppUser> users = userRepository.findByUsernameContaining(partOfName);
            if (fromNewToOld)
                requestPages=  requestRepository.findByStatusAndAuthorInOrderByCreated(RequestStatus.SENT, users, pageable);
            else
                requestPages= requestRepository.findByStatusAndAuthorInOrderByCreatedDesc(RequestStatus.SENT, users, pageable);
        } else {
            if (fromNewToOld)
                requestPages= requestRepository.findByStatusOrderByCreated(pageable, RequestStatus.SENT);
            else
                requestPages= requestRepository.findByStatusOrderByCreatedDesc(pageable, RequestStatus.SENT);
        }
        requestPages.forEach(r->r.setMessage(formatTextWithDash(r.getMessage())));
        return requestPages;
    }

    public boolean operatorChangeRequestStatus(long id, boolean received) {
        Request request = requestRepository.findById(id).orElse(null);
        if (request!=null
                && (request.getStatus()==RequestStatus.SENT)){
            if (received)
                request.setStatus(RequestStatus.RECEIVED);
            else
                request.setStatus(RequestStatus.REJECTED);
            requestRepository.save(request);
            return true;
        } else
            return false;
    }

    private String formatTextWithDash(String text){
        return String.join("-",text.split(""));
    }

    private AppUser getCurrentUser(){
        return (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
