package com.booking.platform.mybookingshow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.platform.mybookingshow.entity.BkpUser;

@Repository
public interface UserRepository extends JpaRepository<BkpUser, Long> {
	Optional<BkpUser> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
