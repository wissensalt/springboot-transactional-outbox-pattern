package com.wissensalt.springboottransactionaloutboxpattern.repository;

import com.wissensalt.springboottransactionaloutboxpattern.model.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {
    @Query("SELECT l.id FROM LoginHistory l WHERE l.accountId IN (?1)")
    List<Long> findByAccountIdIn(List<String> accountIds);
}
