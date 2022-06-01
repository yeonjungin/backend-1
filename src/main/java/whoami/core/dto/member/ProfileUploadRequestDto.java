package whoami.core.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfileUploadRequestDto {
    private String userId;
    private String dirName="profile";

    public ProfileUploadRequestDto(String userId) {
        this.userId = userId;
    }
}
