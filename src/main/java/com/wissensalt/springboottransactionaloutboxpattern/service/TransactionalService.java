package com.wissensalt.springboottransactionaloutboxpattern.service;

import com.wissensalt.springboottransactionaloutboxpattern.dto.DeleteData;

public interface TransactionalService {
    void deleteAccounts(DeleteData deleteData);
    void deleteRollback(DeleteData deleteData);
}
