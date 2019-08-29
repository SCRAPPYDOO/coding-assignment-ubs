package com.ubs.proposal.configuration;

import com.ubs.proposal.mapper.ProposalMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public ProposalMapper proposalMapper() {
        return Mappers.getMapper(ProposalMapper.class);
    }
}
