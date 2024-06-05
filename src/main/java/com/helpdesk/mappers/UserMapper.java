package com.helpdesk.mappers;

import com.helpdesk.Utils.RolEnum;
import com.helpdesk.model.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users WHERE usuario_id = #{id}")
    @Results({
            @Result(property = "usuarioId", column = "usuario_id"),
            @Result(property = "nombre", column = "nombre"),
            @Result(property = "apellidos", column = "apellidos"),
            @Result(property = "area", column = "area"),
            @Result(property = "rol", column = "rol"),
            @Result(property = "requerimientos", column = "usuario_id",
                    javaType = List.class,
                    many = @Many(select = "com.helpdesk.mappers.RequirementMapper.findBySolicitanteId"))
    })
    Users findById(Long id);


    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "usuarioId", column = "usuario_id"),
            @Result(property = "nombre", column = "nombre"),
            @Result(property = "apellidos", column = "apellidos"),
            @Result(property = "area", column = "area"),
            @Result(property = "rol", column = "rol"),
            @Result(property = "requerimientos", column = "usuario_id",
                    javaType = List.class,
                    many = @Many(select = "com.helpdesk.mappers.RequirementMapper.findBySolicitanteId"))
    })
    List<Users> findAll();

    @Select("SELECT * FROM users where rol = #{rol}")
    @Results({
            @Result(property = "usuarioId", column = "usuario_id"),
            @Result(property = "nombre", column = "nombre"),
            @Result(property = "apellidos", column = "apellidos"),
            @Result(property = "area", column = "area"),
            @Result(property = "rol", column = "rol")
    })
    List<Users> findItAnalyst(RolEnum rol);



    @Insert("INSERT INTO users (nombre, apellidos, area, rol) VALUES (#{nombre}, #{apellidos}, #{area}, #{rol})")
    void insert(Users user);


    @Update("UPDATE users SET nombre = #{user.nombre}, apellidos = #{user.apellidos}, area = #{user.area}, rol = #{user.rol} WHERE usuario_id = #{usuarioId}")
    void updateUser(@Param("user") Users user, @Param("usuarioId") Long usuarioId);

    @Delete("DELETE FROM users WHERE usuario_id = #{usuarioId}")
    void deleteUserById(@Param("usuarioId") Long usuarioId);
}
