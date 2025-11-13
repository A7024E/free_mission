package shopping.backend.member;

import java.util.Objects;

public class Password {
    private String password;

    public Password(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    private void validate(String password){
        if(password.length() < 12){
            throw new IllegalArgumentException("패스워드는 12자리로 입력해야합니다");
        }

        if(password == null || password.isEmpty()){
            throw new IllegalArgumentException("패스워드는 공백이 될 수 없습니다");
        }

        if(password.isBlank()){
            throw new IllegalArgumentException("패스워드는 빈칸을 입력할 수 없습니다");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Password password1 = (Password) o;
        return Objects.equals(password, password1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(password);
    }
}
