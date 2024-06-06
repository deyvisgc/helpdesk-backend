package com.helpdesk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.helpdesk.Utils.EstadoEnum;
import lombok.Data;

@Data
public class Requirement {
    private Long requerimiento_id;
    private Long solicitante_id;
    private String nombre_solicitud;
    private String descripcion;
    private String archivo_adjunto;
    private EstadoEnum estado;
    private Users asignado;
    private Users solicitante;
}
