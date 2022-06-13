package com.wissensalt.springboottransactionaloutboxpattern.service.impl;

import com.github.javafaker.Faker;
import com.wissensalt.springboottransactionaloutboxpattern.model.Account;
import com.wissensalt.springboottransactionaloutboxpattern.model.LoginHistory;
import com.wissensalt.springboottransactionaloutboxpattern.repository.AccountRepository;
import com.wissensalt.springboottransactionaloutboxpattern.repository.LoginHistoryRepository;
import com.wissensalt.springboottransactionaloutboxpattern.service.InquiryService;
import com.wissensalt.springboottransactionaloutboxpattern.service.OrchestratorService;
import com.wissensalt.springboottransactionaloutboxpattern.service.PublisherService;
import com.wissensalt.springboottransactionaloutboxpattern.service.TransactionalService;
import io.reactivex.Completable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrchestratorServiceImpl implements OrchestratorService {
    private final AccountRepository accountRepository;
    private final LoginHistoryRepository loginHistoryRepository;

    private final InquiryService inquiryService;
    private final TransactionalService transactionalService;
    private final PublisherService publisherService;

    @Transactional
    @Override
    public void generateData(int totalAccounts, int totalLoginHistoryPerAccount) {
        final Faker faker = new Faker();
        final List<Account> accountList = new ArrayList<>();
        final List<LoginHistory> loginHistoryList = new ArrayList<>();
        for (int a = 0; a < totalAccounts; a++) {
            Account account = new Account();
            account.setId(UUID.randomUUID());
            account.setName(faker.name().fullName());
            account.setAddress(faker.address().fullAddress());
            accountList.add(account);

            for (int b = 0; b < totalLoginHistoryPerAccount; b++) {
                LoginHistory loginHistory = new LoginHistory();
                loginHistory.setAccountId(account.getId().toString());
                loginHistory.setLoginAt(new Date());
                loginHistoryList.add(loginHistory);
            }
        }

        accountRepository.saveAll(accountList);
        loginHistoryRepository.saveAll(loginHistoryList);
    }

    @Override
    public Completable deleteViaTransactionalOutboxPattern() {
        return Completable.create(completableEmitter ->
                inquiryService.buildDeleteData()
                        .subscribe(deleteData -> {
                            transactionalService.deleteAccounts(deleteData);
                            publisherService.publishDeletedData(deleteData);
                        })
        );
    }

    @Override
    public Completable deleteRollback() {
        return Completable.create(completableEmitter ->
                inquiryService.buildDeleteData()
                        .subscribe(deleteData -> {
                            transactionalService.deleteRollback(deleteData);
                            publisherService.publishDeletedData(deleteData);
                        })
        );
    }
}
