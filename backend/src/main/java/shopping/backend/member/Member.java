package shopping.backend.member;

public class Member {

    private Long id;
    private String name;
    private String password;
    private String gender;

    public Member(Long id, String name, String password, String gender) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }
}
