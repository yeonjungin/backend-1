package whoami.core.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class LogoutRequestDto {
    private String accessToken;
    private String refreshToken;

    @Builder
    public LogoutRequestDto(String accessToken, String refreshToken) {
        this.accessToken=accessToken;
        this.refreshToken = refreshToken;
    }
}
