package com.helpdesk.service;

import com.helpdesk.Utils.EstadoEnum;
import com.helpdesk.dto.UsuarioRequerimientoDto;
import com.helpdesk.model.Proposal;
import com.helpdesk.model.Requirement;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProposalService {
    public Proposal getById(Long propuestaId);
    public Long getCount();
    public Long getCountByStatus(EstadoEnum estadoEnum);
    public List<Proposal> getAll();
    public List<Proposal> getByStatus(EstadoEnum estadoEnum);
    public void save(Proposal proposal) throws IOException;
    public void update(Proposal proposal);
    void tomarDecision(Long propuestaId, EstadoEnum estadoEnum);
    public void delete(Long propuestaId);


}
