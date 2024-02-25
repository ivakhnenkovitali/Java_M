package com.hrm.cloud.alpha.service;

import com.hrm.cloud.alpha.client.BravoClient;
import com.hrm.cloud.alpha.dto.MyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyServiceImpl implements MyService {

    private final BravoClient bravoClient;

    @Override
    public MyDto ping(MyDto myDto) {
        log.info("Service invoked with param: {}", myDto);
        return bravoClient.ping(myDto);
    }
}
