package com.wissensalt.springboottransactionaloutboxpattern.repository;

import com.wissensalt.springboottransactionaloutboxpattern.model.LoginHistory;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.List;

public interface LoginHistoryRepository extends ReactiveCrudRepository<LoginHistory, Long> {
    @Query("SELECT l.id FROM LoginHistory l WHERE l.accountId IN (?1)")
    List<Long> findByAccountIdIn(List<String> accountIds);
}
