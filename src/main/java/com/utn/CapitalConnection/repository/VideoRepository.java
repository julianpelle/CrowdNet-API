package com.utn.CapitalConnection.repository;

import com.utn.CapitalConnection.entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<VideoEntity,Long> {
}
