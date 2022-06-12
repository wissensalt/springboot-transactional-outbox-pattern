package com.wissensalt.springboottransactionaloutboxpattern.repository;

import com.wissensalt.springboottransactionaloutboxpattern.model.Account;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends ReactiveCrudRepository<Account, UUID> {
    @Query("SELECT a.id FROM Account a")
    List<UUID> findAllIds();
}
