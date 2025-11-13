package shopping.backend.member;

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
    @Enumerated(EnumType.STRING)
    private Gender gender;

    protected Member() {}

    public Member(MemberId id, Password password, NickName nickName, Gender gender) {
        this.id = id;
        this.password = password;
        this.nickName = nickName;
        this.gender = gender;
    }


}
