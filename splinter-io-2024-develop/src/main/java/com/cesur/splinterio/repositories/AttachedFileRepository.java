package com.cesur.splinterio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cesur.splinterio.models.AttachedFile;
@Repository
public interface AttachedFileRepository extends JpaRepository<AttachedFile, Long>{

}
