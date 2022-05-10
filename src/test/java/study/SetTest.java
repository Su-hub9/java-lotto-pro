package study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class SetTest {

    private Set<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    @DisplayName("요구사항 1: Set 의 크기를 확인")
    @Test
    void test_size() {
        assertThat(numbers.size()).isEqualTo(3);
    }

    @DisplayName("요구사항 2: Set 에 1, 2, 3의 값이 존재하는지 확인")
    @Test
    void test_contains() {
        assertThat(numbers.contains(1)).isTrue();
        assertThat(numbers.contains(2)).isTrue();
        assertThat(numbers.contains(3)).isTrue();
    }

    @DisplayName("요구사항 2: Set 에 1, 2, 3의 값이 존재하는지 확인(ParameterizedTest)")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void test_contains_ParameterizedTest(int input) {
        assertThat(numbers.contains(input)).isTrue();
    }

    @DisplayName("요구사항 3: 입력 값이 Set 에 존재하지 않으면 false 가 반환되는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"1:true", "2:true","3:true", "4:false", "5:false"}, delimiter = ':')
    void test_contains_set_false(int input, boolean expected) {
        assertThat(numbers.contains(input)).isEqualTo(expected);
    }
}
