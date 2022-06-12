package com.wissensalt.springboottransactionaloutboxpattern.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteData {
    private List<UUID> accountIds;
    private List<Long> loginHistoryIds;
}
