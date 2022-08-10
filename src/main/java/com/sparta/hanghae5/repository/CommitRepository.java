package com.sparta.hanghae5.repository;

import com.sparta.hanghae5.model.Commit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommitRepository extends JpaRepository<Commit, Long> {
}
