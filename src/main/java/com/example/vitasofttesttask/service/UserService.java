package com.example.vitasofttesttask.service;

import com.example.vitasofttesttask.entity.AppUser;

import java.util.List;

public interface UserService {
    boolean adminChangeRole(long id, boolean statusOperator);
    List<AppUser> adminFindByPartOfName(String partOfName);
}
