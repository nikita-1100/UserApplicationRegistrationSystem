package com.example.vitasofttesttask.repository;

import com.example.vitasofttesttask.entity.AppUser;
import com.example.vitasofttesttask.entity.Request;
import com.example.vitasofttesttask.entity.RequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    Page<Request> findByAuthorOrderByCreatedDesc(Pageable pageable, AppUser user);

    Page<Request> findByAuthorOrderByCreated(Pageable pageable, AppUser user);

    Page<Request> findByStatusOrderByCreated(Pageable pageable, RequestStatus requestStatus);

    Page<Request> findByStatusOrderByCreatedDesc(Pageable pageable, RequestStatus requestStatus);

    Page<Request> findByStatusAndAuthorInOrderByCreated(RequestStatus status, List<AppUser> authors, Pageable pageable);

    Page<Request> findByStatusAndAuthorInOrderByCreatedDesc(RequestStatus status, List<AppUser> authors, Pageable pageable);
}
