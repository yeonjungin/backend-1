package whoami.core.dto.member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDeleteRequestDto {
    private String userId;

    @Builder
    public MemberDeleteRequestDto(String userId) {
        this.userId = userId;
    }
}
