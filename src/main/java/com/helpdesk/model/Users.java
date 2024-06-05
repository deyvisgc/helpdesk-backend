package com.helpdesk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.helpdesk.Utils.RolEnum;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
//@Builder
public class Users {

    private Long usuarioId;
    @NotBlank(message = "Nombre del solicitante es requerido.")
    private String nombre;
    @NotBlank(message = "Appelido del solicitante es requerido.")
    private String apellidos;
    @NotBlank(message = "Area del solicitante es requerido.")
    private String area;
    @NotNull(message = "Rol del solicitante es requerido.")
    @Enumerated(EnumType.STRING)
    private RolEnum rol;
    private List<Requirement> requerimientos;
}
