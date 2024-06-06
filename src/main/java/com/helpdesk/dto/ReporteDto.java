package com.helpdesk.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReporteDto {
   private Long totalRequerimiento;
   private Long totalRequerimientoPendientes;
   private Long totalRequerimientoAprobados;
   private Long totalPropuestas;
}
