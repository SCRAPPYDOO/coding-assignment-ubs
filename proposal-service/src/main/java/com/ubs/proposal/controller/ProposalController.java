package com.ubs.proposal.controller;

import com.ubs.proposal.dto.CreateProposalDto;
import com.ubs.proposal.dto.ProposalDto;
import com.ubs.proposal.mapper.ProposalMapper;
import com.ubs.proposal.service.ProposalService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proposal")
public class ProposalController {

    private final ProposalService proposalService;
    private final ProposalMapper proposalMapper;

    public ProposalController(final ProposalService proposalService,
                              final ProposalMapper proposalMapper) {
        this.proposalService = proposalService;
        this.proposalMapper = proposalMapper;
    }

    @PostMapping
    public ProposalDto createProposal(@RequestBody final CreateProposalDto proposalDto) {
        return proposalMapper.mapToProposalDto(proposalService.createProposal(proposalMapper.mapToProposal(proposalDto)));
    }
}
