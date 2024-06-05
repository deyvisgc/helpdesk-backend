package com.helpdesk.mappers;
import com.helpdesk.model.UserHistory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserHistoryMapper {
    @Select("SELECT * FROM storiesusers WHERE propuesta_id = #{proposalId}")
    @Results({
            @Result(property = "historiaId", column = "id"),
            @Result(property = "titulo", column = "titulo"),
            @Result(property = "descripcion", column = "descripcion")
            /*
            @Result(property = "proposal", column = "propuesta_id",
                    one = @One(select = "com.helpdesk.mappers.ProposalMapper.findById"))
                    */

    })
    List<UserHistory> getUserStoriesByProposalId(Long proposalId);

    // Otros métodos según sea necesario...
    @Insert({
            "<script>",
            "INSERT INTO storiesusers (titulo, descripcion, prioridad, propuesta_id) VALUES ",
            "<foreach item='epic' collection='history' separator=','>",
            "(#{epic.titulo},#{epic.descripcion}, #{epic.prioridad},  #{epic.propuesta_id})",
            "</foreach>",
            "</script>"
    })
    void insertMultiple(@Param("history") List<UserHistory> history);
}
