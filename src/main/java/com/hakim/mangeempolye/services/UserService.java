package com.hakim.mangeempolye.services;

import java.util.List;

import com.hakim.mangeempolye.domain.Role;
import com.hakim.mangeempolye.domain.UserApp;

public interface UserService {

     UserApp saveUser(UserApp user);
     Role saveRole (Role  role);
     void addRoleToUser(String username ,String rolename);
     UserApp getUser(Long  id );
     List<UserApp> getUsers();
    
    List<Role> getRoles();
    
}