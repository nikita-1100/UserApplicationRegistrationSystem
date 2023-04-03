package com.example.vitasofttesttask.repository;

import com.example.vitasofttesttask.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(String username);
    List<AppUser> findByUsernameContaining(String partOfName);
}
