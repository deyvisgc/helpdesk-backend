package com.helpdesk.dto;

import com.helpdesk.model.Requirement;
import com.helpdesk.model.Users;
import lombok.Data;

@Data
public class UsuarioRequerimientoDto {
   private Users users;
   private Requirement requirement;
}
