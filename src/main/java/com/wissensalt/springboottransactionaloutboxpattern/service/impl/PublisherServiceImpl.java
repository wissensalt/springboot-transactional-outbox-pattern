package com.wissensalt.springboottransactionaloutboxpattern.service.impl;

import com.wissensalt.springboottransactionaloutboxpattern.dto.DeleteData;
import com.wissensalt.springboottransactionaloutboxpattern.service.PublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PublisherServiceImpl implements PublisherService {

    // Do logging for testing purpose to prove that completable will be executed if transactional successfully executed
    @Override
    public void publishDeletedData(DeleteData deleteData) {
        deleteData.getAccountIds()
                .forEach(accountId -> log.info(">>>>>> Deleted Account : {}", accountId));
        deleteData.getLoginHistoryIds()
                .forEach(loginHistoryId -> log.info(">>>>>> Deleted Login History : {}", loginHistoryId));
    }
}
