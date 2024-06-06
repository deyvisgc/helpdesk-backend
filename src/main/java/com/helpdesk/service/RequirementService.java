package com.helpdesk.service;

import com.helpdesk.Utils.EstadoEnum;
import com.helpdesk.dto.UsuarioRequerimientoDto;
import com.helpdesk.model.Requirement;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RequirementService {
    public Requirement getById(Long id);
    public Long getCount();
    public Long getCountByStatus(EstadoEnum estadoEnum);
    public List<Requirement> getByAnalystId(Long id);
    public List<Requirement> getAll();
    public List<Requirement> getRequirementByEstatus(EstadoEnum estado);
    void assignRequirement(Long requirementId, Long asignadoId);
    public void save(UsuarioRequerimientoDto usuarioRequerimientoDto, MultipartFile file) throws IOException;
    public void update(UsuarioRequerimientoDto user, MultipartFile file, Long idUsers, Long idRequerimiento) throws IOException;
    public void delete(Long id);

}
