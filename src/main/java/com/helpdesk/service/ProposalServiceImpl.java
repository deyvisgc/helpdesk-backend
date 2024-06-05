package com.helpdesk.service;

import com.helpdesk.Utils.EstadoEnum;
import com.helpdesk.mappers.EpicMapper;
import com.helpdesk.mappers.ProposalMapper;
import com.helpdesk.mappers.UserHistoryMapper;
import com.helpdesk.model.Epic;
import com.helpdesk.model.Proposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class ProposalServiceImpl implements ProposalService {
    @Autowired
    ProposalMapper proposalMapper;
    @Autowired
    EpicMapper epicMapper;
    @Autowired
    UserHistoryMapper userHistoryMapper;
    @Override
    public Proposal getById(Long id) {
        return proposalMapper.findById(id);
    }

    @Override
    public List<Proposal> getAll() {
        return proposalMapper.findAll();
    }

    @Override
    public List<Proposal> getByStatus(EstadoEnum estadoEnum) {
        return proposalMapper.findByStatus(estadoEnum);
    }

    @Override
    public void save(Proposal proposal) throws IOException {
         proposal.setFechaEnvio(new Date());
         proposal.setEstado(EstadoEnum.EN_PROGRESO);
         proposalMapper.insert(proposal);
         proposal.getEpica().forEach(f -> f.setPropuesta_id(proposal.getPropuestaId()));
         proposal.getHistoriaUsuarios().forEach(h-> h.setPropuesta_id(proposal.getPropuestaId()));
         epicMapper.insertMultiple(proposal.getEpica());
        userHistoryMapper.insertMultiple(proposal.getHistoriaUsuarios());
    }

    @Override
    public void update(Proposal proposal) {

    }

    @Override
    public void tomarDecision(Long propuestaId, EstadoEnum estadoEnum) {
        proposalMapper.tomarDecision(propuestaId, estadoEnum);
    }

    @Override
    public void delete(Long id) {
        proposalMapper.deleteById(id);
    }
}
