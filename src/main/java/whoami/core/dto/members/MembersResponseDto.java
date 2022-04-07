package whoami.core.dto.members;

import lombok.Builder;
import lombok.Getter;
import whoami.core.domain.members.Members;

@Getter
public class MembersResponseDto {

    private final String userId;
    private final String name;
    private final String email;

    @Builder
    public MembersResponseDto(Members entity){
        this.userId = entity.getUserId();
        this.name = entity.getName();
        this.email = entity.getEmail();
    }

}
