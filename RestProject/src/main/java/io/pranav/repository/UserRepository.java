package io.pranav.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.pranav.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
