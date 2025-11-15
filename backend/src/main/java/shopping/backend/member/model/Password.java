package shopping.backend.member.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Password {
    @Column(name = "password")
    private String password;

    public Password(String password) {
        validate(password);
        this.password = password;
    }

    public Password() {
    }

    public String getPassword() {
        return password;
    }

    private void validate(String password){
        if(password.length() < 8){
            throw new IllegalArgumentException("패스워드는 9자리 이상으로 입력해야합니다");
        }

        if(password == null || password.isEmpty()){
            throw new IllegalArgumentException("패스워드는 공백이 될 수 없습니다");
        }

        if(password.isBlank()){
            throw new IllegalArgumentException("패스워드는 빈칸을 입력할 수 없습니다");
        }
    }

    public boolean isSame(Password other){
        return this.password.equals(other.password);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Password password1)) {
            return false;
        }
        return Objects.equals(password, password1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(password);
    }
}
