package com.everyTing.team.adapter.in.web.docs;

import com.everyTing.core.dto.Response;
import com.everyTing.team.domain.TeamWithLikeCount;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@Tag(name = "상대팀 관련 API")
public interface OtherTeamControllerDocs {

    @Operation(summary = "상대팀 조회")
    @PageableAsQueryParam
    Response<Slice<TeamWithLikeCount>> teamList(Long teamId,
        @Schema(hidden = true) Pageable pageable);
}
