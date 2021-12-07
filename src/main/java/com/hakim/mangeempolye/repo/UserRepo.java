package com.hakim.mangeempolye.repo;



import com.hakim.mangeempolye.domain.UserApp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserApp,Long>{

    UserApp findByName(String username);


}
