package com.wissensalt.springboottransactionaloutboxpattern.service.impl;

import com.wissensalt.springboottransactionaloutboxpattern.dto.DeleteData;
import com.wissensalt.springboottransactionaloutboxpattern.repository.AccountRepository;
import com.wissensalt.springboottransactionaloutboxpattern.repository.LoginHistoryRepository;
import com.wissensalt.springboottransactionaloutboxpattern.service.TransactionalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransactionalServiceImpl implements TransactionalService {

    private final AccountRepository accountRepository;
    private final LoginHistoryRepository loginHistoryRepository;

    @Transactional
    @Override
    public void deleteAccounts(DeleteData deleteData) {
        log.info(">>>>>> execute delete accounts");
        accountRepository.deleteAllById(deleteData.getAccountIds());
        loginHistoryRepository.deleteAllById(deleteData.getLoginHistoryIds());
    }

    @Transactional
    @Override
    public void deleteRollback(DeleteData deleteData) {
        accountRepository.deleteAllById(deleteData.getAccountIds());
        loginHistoryRepository.deleteAllById(deleteData.getLoginHistoryIds());

        // do intentional exception
        int a = 10 / 0;
    }
}