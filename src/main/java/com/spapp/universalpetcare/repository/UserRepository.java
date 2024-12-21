package com.spapp.universalpetcare.repository;

import com.spapp.universalpetcare.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
