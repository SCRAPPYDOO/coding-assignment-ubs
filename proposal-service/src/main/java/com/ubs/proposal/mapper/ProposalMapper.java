package com.ubs.proposal.mapper;

import com.ubs.proposal.dto.CreateProposalDto;
import com.ubs.proposal.dto.ProposalDto;
import com.ubs.proposal.model.Proposal;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProposalMapper {

    Proposal mapToProposal(CreateProposalDto createProposalDto);

    ProposalDto mapToProposalDto(Proposal proposal);

    List<ProposalDto> mapToProposalDtoList(List<Proposal> proposals);
}
