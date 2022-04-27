package com.hakim.mangeempolye.controllers;

import java.net.URI;
import java.util.List;

import com.hakim.mangeempolye.domain.Role;
import com.hakim.mangeempolye.domain.UserApp;
import com.hakim.mangeempolye.services.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
private final  UserService userService;

@GetMapping("/api/v1/all")
public ResponseEntity<List<UserApp>> getUsers(){
    return ResponseEntity.ok().body(userService.getUsers());    
}

@GetMapping("/api/v1/all/roles")
public ResponseEntity<List<Role>> getRoles(){
    return ResponseEntity.ok().body(userService.getRoles());    
}
@PostMapping("/api/v1/save")
public ResponseEntity<UserApp> saveUser(@RequestBody UserApp user){

    URI uri= URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("users/api/v1/save").toUriString());
    return ResponseEntity.created(uri).body(userService.saveUser(user));

    
}

@GetMapping("/api/v1/find/{id}")
public ResponseEntity <UserApp> getUserById(@PathVariable("id")Long id){
    UserApp employee=userService.getUser(id);
   return new ResponseEntity<>(employee,HttpStatus.OK);
} 
@PostMapping("/role/api/v1/save")
public ResponseEntity<Role> saveUser(@RequestBody Role role){
    URI uri= URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("users/role/api/v1/save").toUriString());
    return ResponseEntity.created(uri).body(userService.saveRole(role));

    
}

@PostMapping("/role/api/v1/addtouser")
public ResponseEntity<?> addroleToUser(@RequestBody RoleToUserForm form){
   userService.addRoleToUser(form.getUsername(),form.getRoleName());
   return ResponseEntity.ok().build();
    
}

     
}
@Data
class RoleToUserForm{

    private String username;

    private String roleName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}