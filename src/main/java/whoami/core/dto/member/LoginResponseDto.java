package whoami.core.dto.member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponseDto {
    private final String accessToken;
    private final String refreshToken;

    @Builder
    public LoginResponseDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken=refreshToken;
    }
}
