package com.wissensalt.springboottransactionaloutboxpattern.repository;

import com.wissensalt.springboottransactionaloutboxpattern.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    @Query("SELECT a.id FROM Account a")
    List<UUID> findAllIds();
}
