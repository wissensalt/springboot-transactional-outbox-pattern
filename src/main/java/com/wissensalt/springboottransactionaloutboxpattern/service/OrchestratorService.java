package com.wissensalt.springboottransactionaloutboxpattern.service;

import io.reactivex.Completable;

public interface OrchestratorService {
    void generateData(int totalAccounts, int totalLoginHistoryPerAccount);
    Completable deleteViaTransactionalOutboxPattern();
    Completable deleteRollback();
}
