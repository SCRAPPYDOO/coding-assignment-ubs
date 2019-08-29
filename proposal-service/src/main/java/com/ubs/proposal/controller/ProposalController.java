package com.ubs.proposal.controller;

import com.ubs.proposal.dto.AttachCalculationDto;
import com.ubs.proposal.dto.CreateCalculationDto;
import com.ubs.proposal.dto.CreateProposalDto;
import com.ubs.proposal.dto.ProposalDto;
import com.ubs.proposal.mapper.ProposalMapper;
import com.ubs.proposal.service.ProposalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{proposalId}")
    public ProposalDto getProposalById(@PathVariable final Long proposalId) {
        return proposalMapper.mapToProposalDto(proposalService.getProposalById(proposalId));
    }

    @GetMapping
    public List<ProposalDto> getProposals() {
        return proposalMapper.mapToProposalDtoList(proposalService.getProposals());
    }

    @PostMapping("/{proposalId}/calculation")
    public void createProposalCalculation(@PathVariable final Long proposalId, @RequestBody final CreateCalculationDto createCalculationDto) {
        proposalService.createProposalCalculation(proposalId, createCalculationDto);
    }

    @PatchMapping("/{proposalId}/calculation/{calculationId}/attach")
    public void createProposalCalculation(@PathVariable final Long proposalId, @PathVariable final Long calculationId, @RequestBody final AttachCalculationDto attachCalculationDto) {
        proposalService.attachCalculation(proposalId, calculationId, attachCalculationDto);
    }

    @GetMapping("/{proposalId}/email")
    public void sendEmail(@PathVariable final Long proposalId) {
        proposalService.sendEmail(proposalId);
    }
}
