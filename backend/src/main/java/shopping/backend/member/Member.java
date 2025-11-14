package shopping.backend.member;

import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "members")
public class Member {

    @Id
    private MemberId id;
    @Embedded
    private Password password;
    @Embedded
    private NickName nickName;
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    protected Member() {}

    public Member(MemberId id, Password password, NickName nickName, Gender gender) {
        this.id = id;
        this.password = password;
        this.nickName = nickName;
        this.gender = gender;
    }

    public boolean isPasswordMatch(Password inputPassword) {
        return this.password.isSame(inputPassword);
    }

    public void updatePassword(Password newPassword) {
        this.password = newPassword;
    }

    public void updateNickName(NickName newNickName) {
        this.nickName = newNickName;
    }

    public String Id() {
        return id.info();
    }

    public String nickName() {
        return nickName.value();
    }
}
