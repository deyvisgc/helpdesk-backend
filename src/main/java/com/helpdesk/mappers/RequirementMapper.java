package com.helpdesk.mappers;

import com.helpdesk.Utils.EstadoEnum;
import com.helpdesk.model.Requirement;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@Mapper
public interface RequirementMapper {
    @Select("SELECT * FROM requirements WHERE requerimiento_id = #{id}")
    @Results({
            @Result(property = "requerimiento_id", column = "requerimiento_id"),
            @Result(property = "solicitante_id", column = "solicitante_id"),
            @Result(property = "nombre_solicitud", column = "nombre_solicitud"),
            @Result(property = "descripcion", column = "descripcion"),
            @Result(property = "archivo_adjunto", column = "archivo_adjunto"),
            @Result(property = "estado", column = "estado"),
            @Result(property = "solicitante", column = "solicitante_id", one = @One(select = "com.helpdesk.mappers.UserMapper.findById"))
    })
    Requirement findById(Long id);

    @Select("SELECT * FROM requirements WHERE solicitante_id = #{solicitanteId}")
    List<Requirement> findBySolicitanteId(Long solicitanteId);

    @Select("SELECT count(*) FROM requirements")
    Long countRequiriments();

    @Select("SELECT count(*) FROM requirements where estado = #{estado}")
    Long getCountByStatus(EstadoEnum estado);
    @Select("SELECT * FROM requirements WHERE asignado_id = #{analystId}")
    List<Requirement> findByAnaLystId(Long analystId);
    @Select("SELECT * FROM requirements")
    @Results({
            @Result(property = "requerimiento_id", column = "requerimiento_id"),
            @Result(property = "solicitante_id", column = "solicitante_id"),
            @Result(property = "nombre_solicitud", column = "nombre_solicitud"),
            @Result(property = "descripcion", column = "descripcion"),
            @Result(property = "archivo_adjunto", column = "archivo_adjunto"),
            @Result(property = "estado", column = "estado"),
            @Result(property = "solicitante", column = "solicitante_id",
                    one = @One(select = "com.helpdesk.mappers.UserMapper.findById")),
            @Result(property = "asignado", column = "asignado_id",
                    one = @One(select = "com.helpdesk.mappers.UserMapper.findById"))
    })
    List<Requirement> findAll();

    @Select("SELECT * FROM requirements where estado = #{estado}")
    @Results({
            @Result(property = "requerimiento_id", column = "requerimiento_id"),
            @Result(property = "solicitante_id", column = "solicitante_id"),
            @Result(property = "nombre_solicitud", column = "nombre_solicitud"),
            @Result(property = "descripcion", column = "descripcion"),
            @Result(property = "archivo_adjunto", column = "archivo_adjunto"),
            @Result(property = "estado", column = "estado"),
            @Result(property = "solicitante", column = "solicitante_id",
                    one = @One(select = "com.helpdesk.mappers.UserMapper.findById"))
    })
    List<Requirement> getRequirementByEstatus(EstadoEnum estado);

    @Select("CALL sp_save_user_and_requirements(#{p_nombre}, #{p_apellidos}, #{p_area}, #{p_rol}, " +
            "#{p_estado}, #{p_nombre_solicitud}, #{p_descripcion}, #{p_archivo_adjunto})")
    void save(@Param("p_nombre") String p_nombre, @Param("p_apellidos") String p_apellidos,
                         @Param("p_area") String p_area,
                         @Param("p_rol") Enum p_rol,
                         @Param("p_estado") Enum p_estado,
                         @Param("p_nombre_solicitud") String p_nombre_solicitud,
                         @Param("p_descripcion") String p_descripcion,
                         @Param("p_archivo_adjunto") String p_archivo_adjunto
                         );
    @Select("CALL sp_update_user_and_requirements(#{p_usuario_id}, #{p_nombre}, #{p_apellidos}, #{p_area}, #{p_rol}, " +
            "#{p_requerimiento_id}, #{p_nombre_solicitud}, #{p_descripcion}, #{p_archivo_adjunto})")
    void update(
              @Param("p_usuario_id") Integer p_usuario_id,
              @Param("p_nombre") String p_nombre, @Param("p_apellidos") String p_apellidos,
              @Param("p_area") String p_area,
              @Param("p_rol") Enum p_rol,
              @Param("p_requerimiento_id") Integer p_requerimiento_id,
              @Param("p_nombre_solicitud") String p_nombre_solicitud,
              @Param("p_descripcion") String p_descripcion,
              @Param("p_archivo_adjunto") String p_archivo_adjunto
    );
    @Update("UPDATE requirements SET asignado_id = #{asignadoId}, estado = #{estado} WHERE requerimiento_id = #{requirementId}")
    void assignToAnalyst(@Param("asignadoId") Long asignadoId, @Param("estado") EstadoEnum estado, @Param("requirementId") Long requirementId);

    @Delete("DELETE FROM requirements WHERE requerimiento_id = #{requerimientoId}")
    void deleteById(@Param("requerimientoId") Long requerimientoId);
}
