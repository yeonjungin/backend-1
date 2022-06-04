package whoami.core.dto.member;
<<<<<<< HEAD:src/main/java/whoami/core/dto/member/LoginRequestDto.java
=======

>>>>>>> upstream/master:src/main/java/whoami/core/dto/members/LoginRequestDto.java
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequestDto {
    private String userId;
    private String password;

    @Builder
    public LoginRequestDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(userId, password);
    }
}
