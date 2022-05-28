package whoami.core.dto.member;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProfileUploadResponseDto {
    private String profileUrl;

    @Builder
    public ProfileUploadResponseDto(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}
