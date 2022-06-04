package whoami.core.dto.member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfileDeleteRequestDto {
    String userId;

    public ProfileDeleteRequestDto(String userId) {
        this.userId = userId;
    }
}
