package com.helpdesk.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helpdesk.Utils.ApiResponse;
import com.helpdesk.Utils.EstadoEnum;
import com.helpdesk.dto.UsuarioRequerimientoDto;
import com.helpdesk.exception.RequerimientoException;
import com.helpdesk.model.Requirement;
import com.helpdesk.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
@RestController
@RequestMapping("/api/v1/requirement")
public class RequerimientoController {
    @Autowired
    private RequirementService requerimientoService;
    @Autowired
    private static final ApiResponse response = ApiResponse.getInstance();
    @GetMapping
    public ResponseEntity<List<Requirement>> getAll() {
        List<Requirement> requirements = requerimientoService.getAll();
        return ResponseEntity.ok(requirements);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Requirement> getById(@PathVariable Long id) {
        Requirement requirement = requerimientoService.getById(id);
        if (requirement != null) {
            return ResponseEntity.ok(requirement);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/status")
    public ResponseEntity<List<Requirement>> getRequirementsByStatus(@RequestParam("status") EstadoEnum estado) {
        List<Requirement> users = requerimientoService.getRequirementByEstatus(estado);
        return ResponseEntity.ok(users);
    }
    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestParam("datos") String datosJson, @RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RequerimientoException("Archivo no seleccionado");
            }
            if (datosJson.isEmpty()) {
                throw new RequerimientoException("Datos no enviados");
            }
            // Convertir los datos JSON a objetos Java si es necesario
            ObjectMapper objectMapper = new ObjectMapper();
            UsuarioRequerimientoDto usuarioRequerimientoDto = objectMapper.readValue(datosJson, UsuarioRequerimientoDto.class);
            requerimientoService.save(usuarioRequerimientoDto, file);
            response.setSuccess(true);
            response.setMessage("Requerimiento creado exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error interno del servidor: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@RequestParam("idUsuario") Long idUsuario, @RequestParam("idRequerimiento") Long idRequerimiento, @RequestParam("datos") String datosJson, @RequestParam("file") MultipartFile file) {
        try {

            if (file.isEmpty()) {
                throw new RequerimientoException("Archivo no seleccionado");
            }
            if (datosJson.isEmpty()) {
                throw new RequerimientoException("Datos no enviados");
            }
            ObjectMapper objectMapper = new ObjectMapper();
            UsuarioRequerimientoDto usuarioRequerimientoDto = objectMapper.readValue(datosJson, UsuarioRequerimientoDto.class);

            requerimientoService.update(usuarioRequerimientoDto, file, idUsuario, idRequerimiento);

            response.setSuccess(true);
            response.setMessage("Requerimiento actualizado exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error interno del servidor: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @PutMapping("/assign-analyst")
    public ResponseEntity<ApiResponse> asignarAnalista(@RequestParam("requirementId") Long idRequerimiento, @RequestParam("asignadoId") Long asignadoId) {
        try {
            requerimientoService.assignRequirement(idRequerimiento, asignadoId);
            response.setSuccess(true);
            response.setMessage("Requerimiento Asignado exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error interno del servidor: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        try {
            requerimientoService.delete(id);
            response.setSuccess(true);
            response.setMessage("Requerimiento eliminado exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error interno del servidor: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
