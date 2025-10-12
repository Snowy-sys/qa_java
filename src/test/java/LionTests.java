import com.example.Lion;
import com.example.Predator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LionTests {

    @Mock
    private Predator predator;

    private Lion lion;

    @BeforeEach
    @DisplayName("Создание экземпляра Lion с мокнутым Predator")
    void objectsCreationLion() throws Exception{
        lion = new Lion("Самец", predator);
    }

    @Test
    @Tag("Positive")
    @DisplayName("Проверка корректного возвращаемого значения метода getFood для значения Хищник")
    void testGetFoodReturnPredatorFood() throws Exception {
        List<String> expected = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(predator.getFood("Хищник")).thenReturn(expected);

        List<String> actual = lion.getFood();

        assertEquals(expected, actual);
        Mockito.verify(predator, Mockito.times(1)).getFood("Хищник");
    }

    @Test
    @Tag("Negative")
    @DisplayName("Проверка НЕкорректного возвращаемого значения метода getFood для значения Хищник")
    void testGetFoodReturnNotPredatorFood() throws Exception {
        List<String> expected = List.of("Трава", "Различные растения");
        Mockito.when(predator.getFood("Хищник")).thenReturn(expected);

        List<String> actual = lion.getFood();

        assertNotEquals(List.of("Животные", "Птицы", "Рыба"), actual);
        Mockito.verify(predator, Mockito.times(1)).getFood("Хищник");
    }

    @Test
    @Tag("Positive")
    @DisplayName("Проверка количества котят у Льва")
    void testGetKittensReturnQuantity() {
        int expected = 5;

        Mockito.when(predator.getKittens()).thenReturn(expected);

        assertEquals(expected, lion.getKittens());
        Mockito.verify(predator).getKittens();
    }

    @Test
    @Tag("Negative")
    @DisplayName("Проверка исключения, если Predator выбросит ошибку — Лев должен её пробросить")
    void testGetFoodException() throws Exception {
        Mockito.when(predator.getFood("Хищник"))
                .thenThrow(new Exception("Ошибка в источнике пищи"));

        assertThrows(Exception.class, () -> lion.getFood());
        Mockito.verify(predator).getFood("Хищник");
    }
}


