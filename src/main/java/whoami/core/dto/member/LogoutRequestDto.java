package whoami.core.dto.member;
<<<<<<< HEAD:src/main/java/whoami/core/dto/member/LogoutRequestDto.java
=======

>>>>>>> upstream/master:src/main/java/whoami/core/dto/members/LogoutRequestDto.java
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
