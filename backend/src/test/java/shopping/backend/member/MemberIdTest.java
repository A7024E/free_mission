package shopping.backend.member;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MemberIdTest {
    @DisplayName("아이디에 공백이 들어가면 에러가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {" qwer123","qwe 234"," ","qwer1234 "})
    void validateIsNullorEmpty(String ids){
        // given
        // when// then
        assertThatThrownBy(() -> new MemberId(ids))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("아이디에 빈칸이 들어가면 에러가 발생한다")
    @Test
    void validateIsContainsBlank(){
        // given
        // when// then
        assertThatThrownBy(() -> new MemberId(" "))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
