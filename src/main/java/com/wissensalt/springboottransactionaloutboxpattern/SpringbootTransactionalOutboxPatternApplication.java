package com.wissensalt.springboottransactionaloutboxpattern;

import com.wissensalt.springboottransactionaloutboxpattern.service.OrchestratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@SpringBootApplication
public class SpringbootTransactionalOutboxPatternApplication {

    private final OrchestratorService orchestratorService;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTransactionalOutboxPatternApplication.class, args);
    }

    @GetMapping("/generate")
    public boolean generateDummyData(
            @RequestParam("total_accounts") Integer totalAccounts,
            @RequestParam("total_login_history_per_accounts") Integer totalLoginHistoryPerAccounts
    ) {
        orchestratorService.generateData(totalAccounts, totalLoginHistoryPerAccounts);

        return true;
    }

    @DeleteMapping("/delete")
    public Mono<Boolean> deleteAccounts() {
        return Mono.fromSupplier(() -> {
            orchestratorService
                    .deleteViaTransactionalOutboxPattern()
                    .subscribe();

            return true;
        });
    }

    @DeleteMapping("/delete-rollback")
    public Mono<Boolean> deleteRollback() {
        return Mono.fromSupplier(() -> {
            orchestratorService
                    .deleteRollback()
                    .subscribe();

            return true;
        });
    }
}
