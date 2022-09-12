package com.booking.platform.mybookingshow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.platform.mybookingshow.entity.ERole;
import com.booking.platform.mybookingshow.entity.BkpRole;

@Repository
public interface RoleRepository extends JpaRepository<BkpRole, Long> {
	Optional<BkpRole> findByName(ERole name);
}
