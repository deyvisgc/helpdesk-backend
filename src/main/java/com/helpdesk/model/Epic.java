package com.helpdesk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Epic {
    private Long epicId;
    @NotBlank(message = "El titulo de la Épica es requerido")
    private String titulo;
    @NotBlank(message = "La descripciòn de la Épica es requerido")
    private String descripcion;
    private Long propuesta_id;
}
