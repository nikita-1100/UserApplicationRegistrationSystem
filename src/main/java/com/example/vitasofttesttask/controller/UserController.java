package com.example.vitasofttesttask.controller;

import com.example.vitasofttesttask.entity.AppUser;
import com.example.vitasofttesttask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("admin/users")
    public List<AppUser> findSentRequests(
            @RequestParam(value = "partOfName", required = false, defaultValue = "") String partOfName){
        return userService.adminFindByPartOfName(partOfName);
    }

    @PutMapping("admin/users/{id}")
    public HttpStatus changeRole(@PathVariable("id") long id,
            @RequestParam(value = "statusOperator") boolean statusOperator){
        final boolean updated = userService.adminChangeRole(id, statusOperator);
        return updated ? HttpStatus.OK:HttpStatus.NOT_MODIFIED;
    }

}
