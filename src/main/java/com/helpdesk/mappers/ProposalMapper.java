package com.helpdesk.mappers;
import com.helpdesk.Utils.EstadoEnum;
import com.helpdesk.model.Proposal;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ProposalMapper {
    @Select("SELECT * FROM proposals WHERE propuesta_id = #{id}")
    @Results({
            @Result(property = "propuestaId", column = "propuesta_id"),
            @Result(property = "requerimientoId", column = "requerimiento_id"),
            @Result(property = "analistaId", column = "analista_id"),
            @Result(property = "textoPropuesta", column = "texto_propuesta"),
            @Result(property = "estimacionTiempo", column = "estimacion_tiempo"),
            @Result(property = "numeroProgramadores", column = "numero_programadores"),
            @Result(property = "fechaEnvio", column = "fecha_envio"),
            @Result(property = "estado", column = "estado"),
            @Result(property = "requirement", column = "requerimiento_id",
                    one = @One(select = "com.helpdesk.mappers.RequirementMapper.findById")),
            @Result(property = "analista", column = "analista_id",
                    one = @One(select = "com.helpdesk.mappers.UserMapper.findById")),

            @Result(property = "epica", column = "propuesta_id",
                    javaType = List.class,
                    many = @Many(select = "com.helpdesk.mappers.EpicMapper.getEpicsByProposalId")),

            @Result(property = "historiaUsuarios", column = "propuesta_id",
                    javaType = List.class,
                    many = @Many(select = "com.helpdesk.mappers.UserHistoryMapper.getUserStoriesByProposalId"))
    })
    Proposal findById(Long id);

    @Select("SELECT * FROM proposals")
    @Results({
            @Result(property = "propuestaId", column = "propuesta_id"),
            @Result(property = "requerimientoId", column = "requerimiento_id"),
            @Result(property = "analistaId", column = "analista_id"),
            @Result(property = "textoPropuesta", column = "texto_propuesta"),
            @Result(property = "estimacionTiempo", column = "estimacion_tiempo"),
            @Result(property = "numeroProgramadores", column = "numero_programadores"),
            @Result(property = "fechaEnvio", column = "fecha_envio"),
            @Result(property = "estado", column = "estado"),
            @Result(property = "requirement", column = "requerimiento_id",
                    one = @One(select = "com.helpdesk.mappers.RequirementMapper.findById")),
            @Result(property = "analista", column = "analista_id",
                    one = @One(select = "com.helpdesk.mappers.UserMapper.findById")),

            @Result(property = "epica", column = "propuesta_id",
                    javaType = List.class,
                    many = @Many(select = "com.helpdesk.mappers.EpicMapper.getEpicsByProposalId")),

            @Result(property = "historiaUsuarios", column = "propuesta_id",
                    javaType = List.class,
                    many = @Many(select = "com.helpdesk.mappers.UserHistoryMapper.getUserStoriesByProposalId"))
    })
    List<Proposal> findAll();
    @Select("SELECT count(*) FROM proposals")
    Long getCount();
    @Select("SELECT count(*) FROM proposals where estado = #{estado}")
    Long getCountByStatus(EstadoEnum estado);

    @Select("SELECT * FROM proposals where estado = #{estado}")
    @Results({
            @Result(property = "propuestaId", column = "propuesta_id"),
            @Result(property = "requerimientoId", column = "requerimiento_id"),
            @Result(property = "analistaId", column = "analista_id"),
            @Result(property = "textoPropuesta", column = "texto_propuesta"),
            @Result(property = "estimacionTiempo", column = "estimacion_tiempo"),
            @Result(property = "numeroProgramadores", column = "numero_programadores"),
            @Result(property = "fechaEnvio", column = "fecha_envio"),
            @Result(property = "estado", column = "estado"),
            @Result(property = "requirement", column = "requerimiento_id",
                    one = @One(select = "com.helpdesk.mappers.RequirementMapper.findById")),
            @Result(property = "analista", column = "analista_id",
                    one = @One(select = "com.helpdesk.mappers.UserMapper.findById")),

            @Result(property = "epica", column = "propuesta_id",
                    javaType = List.class,
                    many = @Many(select = "com.helpdesk.mappers.EpicMapper.getEpicsByProposalId")),

            @Result(property = "historiaUsuarios", column = "propuesta_id",
                    javaType = List.class,
                    many = @Many(select = "com.helpdesk.mappers.UserHistoryMapper.getUserStoriesByProposalId"))
    })
    List<Proposal> findByStatus(EstadoEnum estado);

    @Insert("INSERT INTO proposals (requerimiento_id, analista_id, texto_propuesta, estimacion_tiempo, numero_programadores, fecha_envio, estado) VALUES (#{requerimientoId}, #{analistaId}, #{textoPropuesta}, #{estimacionTiempo}, #{numeroProgramadores}, #{fechaEnvio}, #{estado})")
    @Options(useGeneratedKeys = true, keyProperty = "propuestaId")
    Long insert(Proposal proposal);
    @Update("UPDATE proposals SET estado = #{estado} WHERE propuesta_id = #{propuestaId}")
    void tomarDecision(@Param("propuestaId") Long propuestaId, @Param("estado") EstadoEnum estado);

    @Delete("DELETE FROM proposals WHERE propuesta_id = #{propuestaId}")
    void deleteById(Long propuestaId);


}
