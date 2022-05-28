package whoami.core.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberUpdateRequestDto {
    private String password;
    private String phoneNum;
    private String email;
    private boolean isReceiveNotification;

    @Builder
    public MemberUpdateRequestDto(String password, String phoneNum, String email, boolean isReceiveNotification) {
        this.password =  password;
        this.phoneNum = phoneNum;
        this.email = email;
        this.isReceiveNotification = isReceiveNotification;
    }
}
