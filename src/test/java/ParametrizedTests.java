import com.example.Lion;
import com.example.Predator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

public class ParametrizedTests {

    @Mock
    private Predator predator;

    public static Object[][] data() {
        return new Object[][] {
                {"Самец"},
                {"Самка"}
        };
    }

    @ParameterizedTest
    @Tag("Positive")
    @MethodSource("data")
    @DisplayName("Проверка пола Льва. Самец или самка")
    public void isSexLionTest(String sex) throws Exception{
        Lion lion = new Lion(sex, predator);

        boolean expected;

        if (sex.equals("Самец")) {
            expected = true;
        } else {
            expected = false;
        }

        boolean HasMane = lion.doesHaveMane();

        assertAll("Проверка конструктора Lion()",
                () -> assertEquals(expected, HasMane, "Пол может быть только Самец или Самка"),
                () -> assertNotNull(HasMane, "Пол не может быть пустым")
        );

    }

    @Test
    @Tag("Negative")
    @DisplayName("Проверка исключения в случае некорректно указанного пола Льва")
    void lionConstructorTrowsException() throws Exception {

        Exception exception = assertThrows(Exception.class, () -> {
            new Lion("Котопёс", predator);
        });

        String expectedMessage = "Используйте допустимые значения пола животного - самей или самка";

        assertEquals(expectedMessage, exception.getMessage());
    }
}
