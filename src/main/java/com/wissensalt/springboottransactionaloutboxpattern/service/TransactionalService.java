package com.wissensalt.springboottransactionaloutboxpattern.service;

import com.wissensalt.springboottransactionaloutboxpattern.dto.DeleteData;
import reactor.core.publisher.Mono;

public interface TransactionalService {
    Mono<Void> deleteAccounts(DeleteData deleteData);
    void deleteRollback(DeleteData deleteData);
}
