package com.helpdesk.model;

import com.helpdesk.Utils.EstadoEnum;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class Proposal {
    private Long propuestaId;
    @NotNull(message = "El requerimiento no puede ser nulo")
    @Min(value = 1, message = "Debe seleccionar un requerimiento")
    private Long requerimientoId;
    @NotNull(message = "El analista no puede ser nulo")
    @Min(value = 1, message = "Debe seleccionar un analista")
    private Long analistaId;
    @NotBlank(message = "El texto de la propuesta es requerida")
    private String textoPropuesta;
    @NotNull(message = "La estimacion del tiempono puede ser nulo")
    private String estimacionTiempo;
    @Min(value = 1, message = "El numero de programadores debe ser mayor a 0")
    private Integer numeroProgramadores;
    private Date fechaEnvio;
    private EstadoEnum estado;
    @Valid
    List<Epic> epica;
    @Valid
    List<UserHistory> historiaUsuarios;
    Requirement requirement;
    Users analista;
}
