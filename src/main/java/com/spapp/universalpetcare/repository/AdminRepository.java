package com.spapp.universalpetcare.repository;

import com.spapp.universalpetcare.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
