package com.hakim.mangeempolye.repo;

import com.hakim.mangeempolye.domain.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long>{

    Role findByname(String name);

    }