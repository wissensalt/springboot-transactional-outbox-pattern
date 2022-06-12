package com.wissensalt.springboottransactionaloutboxpattern.service.impl;

import com.wissensalt.springboottransactionaloutboxpattern.dto.DeleteData;
import com.wissensalt.springboottransactionaloutboxpattern.repository.AccountRepository;
import com.wissensalt.springboottransactionaloutboxpattern.repository.LoginHistoryRepository;
import com.wissensalt.springboottransactionaloutboxpattern.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InquiryServiceImpl implements InquiryService {
    private final AccountRepository accountRepository;
    private final LoginHistoryRepository loginHistoryRepository;

    @Transactional(readOnly = true)
    @Override
    public Mono<DeleteData> buildDeleteData() {
        return Mono.create(emitter -> {
            CompletableFuture<DeleteData> future = CompletableFuture.supplyAsync(() -> {
                final List<UUID> accountIds = accountRepository.findAllIds();
                final List<Long> loginHistoryIds = loginHistoryRepository
                        .findByAccountIdIn(accountIds.stream().map(UUID::toString).collect(Collectors.toList()));

                return DeleteData.builder()
                        .accountIds(accountIds)
                        .loginHistoryIds(loginHistoryIds)
                        .build();
            });

            future.whenComplete(((deleteData, throwable) -> {
                if (throwable == null) {
                    emitter.success(deleteData);
                } else {
                    emitter.error(throwable);
                }
            }));
        });
    }
}
