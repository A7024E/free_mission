package shopping.backend.member;

import java.util.Objects;

public class MemberId {
    private String id;

    public MemberId(String id) {
        validate(id);
        this.id = id;
    }

    public void validate(String id){
        if(id == null || id.isEmpty()){
            throw new IllegalArgumentException("아이디에 공백을 입력할 수 없습니다");
        }

        if(id.contains(" ")){
            throw new IllegalArgumentException("아이디에 빈칸을 입력할수 없습니다");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MemberId memberId = (MemberId) o;
        return Objects.equals(id, memberId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
