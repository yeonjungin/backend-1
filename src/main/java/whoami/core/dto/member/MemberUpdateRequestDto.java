package whoami.core.dto.member;
<<<<<<< HEAD:src/main/java/whoami/core/dto/member/MemberUpdateRequestDto.java
=======

>>>>>>> upstream/master:src/main/java/whoami/core/dto/members/MembersUpdateRequestDto.java
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberUpdateRequestDto {
<<<<<<< HEAD:src/main/java/whoami/core/dto/member/MemberUpdateRequestDto.java
    private String userId;
=======
>>>>>>> upstream/master:src/main/java/whoami/core/dto/members/MembersUpdateRequestDto.java
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
