package com.wissensalt.springboottransactionaloutboxpattern.service;

import com.wissensalt.springboottransactionaloutboxpattern.dto.DeleteData;
import reactor.core.publisher.Mono;

public interface InquiryService {
    Mono<DeleteData> buildDeleteData();
}
