package com.example.vitasofttesttask.service;

import com.example.vitasofttesttask.entity.AppUser;
import com.example.vitasofttesttask.entity.Role;
import com.example.vitasofttesttask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public List<AppUser> adminFindByPartOfName(String partOfName) {
        if (partOfName.length()>0){
            return userRepository.findByUsernameContaining(partOfName);
        } else
            return userRepository.findAll();
    }

    public boolean adminChangeRole(long id, boolean statusOperator) {
        AppUser user = userRepository.findById(id).orElse(null);
        if (user==null || user.getRoles().contains(Role.ADMIN))
            return false;
        else {
            Set<Role> roles = user.getRoles();
            if (statusOperator){
                roles.add(Role.OPERATOR);
                user.setRoles(roles);
            } else{
                roles.remove(Role.OPERATOR);
                user.setRoles(roles);
            }
            userRepository.save(user);
            return true;
        }
    }
}
