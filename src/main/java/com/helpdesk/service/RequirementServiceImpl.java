package com.helpdesk.service;

import com.helpdesk.Utils.EstadoEnum;
import com.helpdesk.dto.UsuarioRequerimientoDto;
import com.helpdesk.mappers.RequirementMapper;
import com.helpdesk.model.Requirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class RequirementServiceImpl implements RequirementService {
    @Autowired
    RequirementMapper requirementMapper;
    @Value("${upload.path}")
    private String uploadPath; // Ruta donde se guardarán los archivos en el servidor

    @Override
    public Requirement getById(Long id) {
       return requirementMapper.findById(id);
    }

    @Override
    public Long getCount() {
        return  requirementMapper.countRequiriments();
    }

    @Override
    public Long getCountByStatus(EstadoEnum estadoEnum) {
        return requirementMapper.getCountByStatus(estadoEnum);
    }

    @Override
    public List<Requirement> getByAnalystId(Long id) {
        return requirementMapper.findByAnaLystId(id);
    }

    @Override
    public List<Requirement> getAll() {
        return requirementMapper.findAll();
    }

    @Override
    public List<Requirement> getRequirementByEstatus(EstadoEnum estado) {
        return requirementMapper.getRequirementByEstatus(estado);
    }

    @Override
    public void assignRequirement(Long requirementId, Long asignadoId) {
        requirementMapper.assignToAnalyst(asignadoId, EstadoEnum.ASIGNADO_ANALISTA, requirementId);
    }

    @Override
    public void save(UsuarioRequerimientoDto requerimientoDto, MultipartFile file) throws IOException {
        // Crear el directorio si no existe
        Files.createDirectories(Paths.get(uploadPath));

        // Guardar el archivo en el servidor
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Files.copy(file.getInputStream(), Paths.get(uploadPath).resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

        // Obtener la URL del archivo guardado
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/download/")
                .path(fileName)
                .toUriString();
        requirementMapper.save(
                requerimientoDto.getUsers().getNombre(),
                requerimientoDto.getUsers().getApellidos(),
                requerimientoDto.getUsers().getArea(),
                requerimientoDto.getUsers().getRol(),
                EstadoEnum.PENDIENTE_SOLICITUD,
                requerimientoDto.getRequirement().getNombre_solicitud(),
                requerimientoDto.getRequirement().getDescripcion(),
                fileDownloadUri
        );
    }
    @Transactional

    @Override
    public void update(UsuarioRequerimientoDto requerimientoDto, MultipartFile file, Long idUser, Long idRequerimiento) throws IOException {
        // Crear el directorio si no existe
        String fileDownloadUri = "";
        if (file != null) {
            Files.createDirectories(Paths.get(uploadPath));
            // Guardar el archivo en el servidor
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), Paths.get(uploadPath).resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

            // Obtener la URL del archivo guardado
             fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/download/")
                    .path(fileName)
                    .toUriString();
        } else {
            fileDownloadUri = requerimientoDto.getRequirement().getArchivo_adjunto();
        }


        requirementMapper.update(
                Math.toIntExact(idUser) ,
                requerimientoDto.getUsers().getNombre(),
                requerimientoDto.getUsers().getApellidos(),
                requerimientoDto.getUsers().getArea(),
                requerimientoDto.getUsers().getRol(),
                Math.toIntExact(idRequerimiento) ,
                requerimientoDto.getRequirement().getNombre_solicitud(),
                requerimientoDto.getRequirement().getDescripcion(),
                fileDownloadUri
        );
    }
    @Transactional

    @Override
    public void delete(Long id) {
        requirementMapper.deleteById(id);
    }
}
