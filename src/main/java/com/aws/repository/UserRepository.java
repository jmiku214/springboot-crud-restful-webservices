package com.aws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aws.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
