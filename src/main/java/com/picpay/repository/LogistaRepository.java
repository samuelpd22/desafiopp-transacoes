package com.picpay.repository;

import com.picpay.entity.LogistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogistaRepository extends JpaRepository<LogistaEntity, Long> {
}
