package whoami.core.dto.member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReissueTokenResponseDto {
    private final String accessToken;
    private final String refreshToken;

    @Builder
    public ReissueTokenResponseDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken=refreshToken;
    }
}
