package whoami.core.dto.members;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MembersDeleteRequestDto {
    private String userId;

    @Builder
    public MembersDeleteRequestDto(String userId) {
        this.userId = userId;
    }
}
