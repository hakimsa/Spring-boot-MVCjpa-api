package com.hakim.mangeempolye.services;

import java.util.List;

import javax.transaction.Transactional;

import com.hakim.mangeempolye.domain.Role;
import com.hakim.mangeempolye.domain.UserApp;
import com.hakim.mangeempolye.repo.RoleRepo;
import com.hakim.mangeempolye.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor @Transactional
public class UserServiceImplemnts implements UserService{
    @Autowired  private  UserRepo userRep ;
    @Autowired  private  RoleRepo roleRepo ;

    @Override
    public UserApp saveUser(UserApp user) {
        // TODO Auto-generated method stub
        //log.info("saving user",user.getName());
        return userRep.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        // TODO Auto-generated method stub
    //    log.info("saving Role  ",role.getName());
        return roleRepo.save(role);
    }



    @Override
    public List<UserApp> getUsers() {
        // TODO Auto-generated method stub
        return userRep.findAll();
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
      //  log.info("add  role to user  ",username + " with role  " + rolename);
        UserApp user=userRep.findByName(username);
        Role role =roleRepo.findByname(rolename);
        user.getRoles().add(role);
        
    }

    @Override
    public List<Role> getRoles() {
        // TODO Auto-generated method stub
        return roleRepo.findAll();
    }

    @Override
    public UserApp getUser(Long id) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
