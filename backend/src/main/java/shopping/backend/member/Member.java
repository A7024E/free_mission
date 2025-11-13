package shopping.backend.member;

public class Member {

    private MemberId id;
    private Password password;
    private NickName nickName;
    private Gender gender;

    public Member(MemberId id, Password password, NickName nickName, Gender gender) {
        this.id = id;
        this.password = password;
        this.nickName = nickName;
        this.gender = gender;
    }

}
