package shopping.backend.member.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "members")
public class Member {

    @EmbeddedId
    private MemberId id;
    @Embedded
    private Password password;
    @Embedded
    private NickName nickName;
    @Convert(converter = GenderConverter.class)
    private Gender gender;
    @Embedded
    private Point point;

    protected Member() {}

    public Member(MemberId id, Password password, NickName nickName, Gender gender) {
        this.id = id;
        this.password = password;
        this.nickName = nickName;
        this.gender = gender;
        this.point = new Point(50000);
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
    public String gender(){
        return Gender.getLabel(gender);
    }
    public int remain() {
        return point.remain();
    }

    public void usePoint(int amount) {
       this.point.minus(amount);
    }

}
