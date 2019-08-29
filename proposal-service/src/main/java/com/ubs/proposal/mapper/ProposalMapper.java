package com.ubs.proposal.mapper;

import com.ubs.proposal.dto.CreateProposalDto;
import com.ubs.proposal.dto.ProposalDto;
import com.ubs.proposal.model.Proposal;
import org.mapstruct.Mapper;

@Mapper
public interface ProposalMapper {

    Proposal mapToProposal(CreateProposalDto createProposalDto);

    ProposalDto mapToProposalDto(Proposal proposal);
}
