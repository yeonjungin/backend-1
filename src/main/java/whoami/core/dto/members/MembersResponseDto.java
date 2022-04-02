package whoami.core.dto.members;

import lombok.Getter;
import whoami.core.domain.members.Members;

@Getter
public class MembersResponseDto {

    private Long id;
    private String userId;
    private String name;
    private String email;

    public MembersResponseDto(Members entity){
        this.id=entity.getMemberId();
        this.userId = entity.getUserId();
        this.name = entity.getName();
        this.email = entity.getEmail();
    }
}
