package com.demo.SpringBootApplication.Repository;

import com.demo.SpringBootApplication.Entity.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {


}
