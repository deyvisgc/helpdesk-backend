package com.helpdesk.mapperStruct;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UsersMapperStruct {
     /*
    @Mappings({
            @Mapping(source = "name", target = "nombre"),
            @Mapping(source = "gender", target = "genero"),
            @Mapping(source = "age", target = "edad"),
            @Mapping(source = "identification", target = "identificacion"),
            @Mapping(source = "address", target = "direccion"),
            @Mapping(source = "phone", target = "telefono"),
            @Mapping(source = "status", target = "estado"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "id", target = "id")
    })
    UsersDto clienteToClienteDTO(Users cliente);
    @InheritInverseConfiguration
    Users clienteDTOtoCliente(UsersDto clienteDTO);

      */
}
