package org.alex.hw19.repository;

import org.alex.hw19.domain.PhotoFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoFileRepository extends JpaRepository<PhotoFile, String> {


}
