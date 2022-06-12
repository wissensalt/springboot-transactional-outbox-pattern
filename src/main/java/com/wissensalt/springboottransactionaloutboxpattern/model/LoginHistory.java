package com.wissensalt.springboottransactionaloutboxpattern.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "login_history")
public class LoginHistory {

    @Id
    private Long id;

    private String accountId;

    private Date loginAt;
}
