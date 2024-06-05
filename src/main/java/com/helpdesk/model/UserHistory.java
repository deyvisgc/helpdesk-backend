package com.helpdesk.model;
import com.helpdesk.Utils.PrioridadEnum;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserHistory {
    private Long historiaId;
    @NotBlank(message = "El titulo de la historia de usuario es requerido")
    private String titulo;
    @NotBlank(message = "La descripcion de la historia de usuario es requerido")
    private String descripcion;
    @NotNull(message = "La prioridad de la historia de usuario es requerido.")
    @Enumerated(EnumType.STRING)
    private PrioridadEnum prioridad;
    private Long propuesta_id;
    /*
    @JsonIgnore
    private Proposal proposal;

     */
}
