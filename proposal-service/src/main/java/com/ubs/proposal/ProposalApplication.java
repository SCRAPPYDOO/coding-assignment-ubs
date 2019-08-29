package com.ubs.proposal;

import com.ubs.proposal.queue.CalculationStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(CalculationStream.class)
public class ProposalApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProposalApplication.class, args);
    }
}
