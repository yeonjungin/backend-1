package whoami.core.dto.member;
import lombok.Builder;
import lombok.Getter;
import whoami.core.domain.member.Member;

@Getter
public class MemberResponseDto {
    private final String userId;
    private final String name;
    private final String email;

    @Builder
    public MemberResponseDto(Member entity){
        this.userId = entity.getUserId();
        this.name = entity.getName();
        this.email = entity.getEmail();
    }
}