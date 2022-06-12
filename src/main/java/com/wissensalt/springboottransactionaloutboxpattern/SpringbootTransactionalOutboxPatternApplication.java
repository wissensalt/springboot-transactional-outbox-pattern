package com.wissensalt.springboottransactionaloutboxpattern;

import com.wissensalt.springboottransactionaloutboxpattern.service.OrchestratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@EnableR2dbcRepositories
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
    public boolean deleteAccounts() {
        orchestratorService
                .deleteViaTransactionalOutboxPattern()
                .subscribe();

        return true;
    }

    @DeleteMapping("/delete-rollback")
    public boolean deleteRollback() {
        orchestratorService
                .deleteRollback()
                .subscribe();

        return true;
    }
}
