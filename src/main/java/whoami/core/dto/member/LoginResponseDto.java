package whoami.core.dto.member;
<<<<<<< HEAD:src/main/java/whoami/core/dto/member/LoginResponseDto.java
=======

>>>>>>> upstream/master:src/main/java/whoami/core/dto/members/LoginResponseDto.java
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
