package whoami.core.dto.members;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MembersUpdateRequestDto {
    private String userId;
    private String password;
    private String phoneNum;
    private String email;
    private boolean isReceiveNotification;

    @Builder
    public MembersUpdateRequestDto(String password, String phoneNum, String email, boolean isReceiveNotification) {
        this.password =  password;
        this.phoneNum = phoneNum;
        this.email = email;
        this.isReceiveNotification = isReceiveNotification;
    }
}
