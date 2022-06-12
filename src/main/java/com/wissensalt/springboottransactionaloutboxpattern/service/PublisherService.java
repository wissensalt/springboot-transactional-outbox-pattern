package com.wissensalt.springboottransactionaloutboxpattern.service;

import com.wissensalt.springboottransactionaloutboxpattern.dto.DeleteData;

public interface PublisherService {
    void publishDeletedData(DeleteData deleteData);
}
