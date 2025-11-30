import com.example.Cat;
import com.example.Feline;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
public class CatTests {

    private Cat cat;

    @Mock
    private Feline felineMock;

    @BeforeEach
    @DisplayName("Создание экземпляра Cat с мокнутым Feline")
    void objectsCreationCatAndFeline() {
        cat = new Cat(felineMock);
    }

    @Test
    @Tag("Positive")
    @DisplayName("Проверка дневного рациона кота с моками")
    void testGetFoodCallVerify() throws Exception {
        List<String> expected = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(felineMock.eatMeat()).thenReturn(expected);

        List<String> actual = cat.getFood();

        assertEquals(expected, actual);
        Mockito.verify(felineMock, Mockito.times(1)).eatMeat();
    }

    @Test
    @Tag("Positive")
    @DisplayName("Проверка рациона кота с реальными объектами")
    void testGetFood() throws Exception{
        Feline feline = new Feline();
        feline.eatMeat();

        Cat cat = new Cat(feline);
        List<String> actual = cat.getFood();

        assertEquals(List.of("Животные", "Птицы", "Рыба"), actual);

    }

    @Test
    @Tag("Negative")
    @DisplayName("Проверка некорректного рациона кота")
    void testGetFoodNotCorrect() throws Exception{
        Feline feline = new Feline();
        feline.eatMeat();

        Cat cat = new Cat(feline);
        List<String> actual = cat.getFood();

        assertNotEquals(List.of("Трава", "Различные растения"), actual);

    }

    @Test
    @Tag("Positive")
    @DisplayName("Позитивная проверка голоса кота")
    void testFunctionCatSoundMua() {
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    @Tag("Negative")
    @DisplayName("Негативная проверка голоса кота")
    void testFunctionCatSoundGav() {
        assertNotEquals("Гав", cat.getSound());
    }
}
