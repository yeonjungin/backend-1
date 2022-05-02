package whoami.core.domain.members;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "userId")
@Table(name="members")
public class Members implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long memberId;

    @Column(nullable = false, unique = true,name="user_id")
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column
    private String name;

    @Column(name="registry_num")
    private String registryNum;

    @Column(name="phone_num")
    private String phoneNum;

    @Column(nullable = false)
    private String email;

    @Column(name="is_receive_notification")
    private boolean isReceiveNotification;

    @Column(name="is_admin")
    private String role;

    @Column
    private String profile;

    @Builder
    public Members(String userId, String password, String name, String registryNum, String phoneNum, String email, boolean isReceiveNotification, String role, String profile) {
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

    public void update(String password,String phoneNum,String email,boolean isReceiveNotification){
        this.password=password;
        this.phoneNum=phoneNum;
        this.email=email;
        this.isReceiveNotification=isReceiveNotification;
    }

    public void update(String profile){
        this.profile=profile;
    }


    public void profileUpdate(String profile){
        this.profile=profile;
    }

    // NOTE : 유저의 권한 목록
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(() -> "ROLE_"+getRole());
        return collectors;
    }

    @Override
    public String getUsername() {
        return getUserId();
    }

    // NOTE : 계정 만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // NOTE : 계정 잠김 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // NOTE : 비밀번호 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // NOTE : 사용자 활성화 여부
    @Override
    public boolean isEnabled() {
        return true;
    }
}

