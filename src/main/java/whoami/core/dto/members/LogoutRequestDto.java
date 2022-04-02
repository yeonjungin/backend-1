package whoami.core.dto.members;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LogoutRequestDto {
    String refreshToken;

    @Builder
    public LogoutRequestDto(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
