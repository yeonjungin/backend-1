package whoami.core.dto.members;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import whoami.core.domain.Role;
import whoami.core.domain.members.Members;


@Getter
@Setter
@NoArgsConstructor
public class MembersSaveRequestDto {
    private String userId;
    private String password;
    private String name;
    private String registryNum;
    private String phoneNum;
    private String email;
    private boolean isReceiveNotification;
    private String role;
    private String profile;

    @Builder
    public MembersSaveRequestDto(String userId, String password, String name, String registryNum, String phoneNum, String email, boolean isReceiveNotification, String role, String profile) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.registryNum = registryNum;
        this.phoneNum = phoneNum;
        this.email = email;
        this.isReceiveNotification = isReceiveNotification;
        this.role = role;
        this.profile = profile;
    }

    public Members toEntity(){
        return Members.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .registryNum(registryNum)
                .phoneNum(phoneNum)
                .email(email)
                .isReceiveNotification(isReceiveNotification)
                .role(role) // FIXME
                .profile(profile)
                .build();
    }
}
