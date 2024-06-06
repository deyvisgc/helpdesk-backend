package com.helpdesk.mappers;

import com.helpdesk.model.Epic;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EpicMapper {
    @Insert({
            "<script>",
            "INSERT INTO epics (titulo, descripcion, propuesta_id) VALUES ",
            "<foreach item='epic' collection='epicas' separator=','>",
            "(#{epic.titulo},#{epic.descripcion} , #{epic.propuesta_id})",
            "</foreach>",
            "</script>"
    })
    void insertMultiple(@Param("epicas") List<Epic> epicas);

    // Métodos de inserción y selección de épicas...

    @Select("SELECT * FROM epics WHERE propuesta_id = #{proposalId}")
    @Results({
            @Result(property = "epicId", column = "epica_id"),
            @Result(property = "nombre", column = "nombre_epic"),
            @Result(property = "descripcion", column = "descripcion")

    })
    List<Epic> getEpicsByProposalId(Long proposalId);
}
