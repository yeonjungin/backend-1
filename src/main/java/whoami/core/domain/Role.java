package whoami.core.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    // FIXME : 이대로 고치기
    USER("USER"),
    ADMIN("ADMIN");
<<<<<<< HEAD
=======

>>>>>>> upstream/master
    private final String value;
}
