import com.example.Feline;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class FelineTests {

    @Spy
    Feline felineSpy = new Feline();

    @Mock
    Feline felineMock;

    @Test
    @Tag("Positive")
    @DisplayName("Проверка метода getKittens с параметром, используя шпион")
    void testSpyFeline(){
        assertEquals(1, felineSpy.getKittens());
        Mockito.verify(felineSpy).getKittens(eq(1));
    }

    @Test
    @Tag("Positive")
    @DisplayName("Проверка пищевого рациона кошачьих")
    void testEatMeatPredator() throws Exception {
        Feline feline = new Feline();
        assertEquals(List.of("Животные", "Птицы", "Рыба"), feline.eatMeat());
    }

    // ОК
    @Test
    @Tag("Positive")
    @DisplayName("Проверка пищего рациона кошачьих с использованием мока")
    void testEatMeatPredatorOnMock() throws Exception{
        Mockito.when(felineMock.eatMeat())
                .thenReturn(List.of("Животные", "Птицы", "Рыба"));

        assertEquals(List.of("Животные", "Птицы", "Рыба"), felineMock.eatMeat());
        Mockito.verify(felineMock).eatMeat();
    }

    @Test
    @Tag("Negative")
    @DisplayName("Тест на случай, если кошачий оказался травоядным :)")
    void testEatMeatNotPredator() throws Exception{
        List<String> herb = felineSpy.getFood("Травоядное");
        Mockito.doReturn(herb).when(felineSpy).eatMeat();

        assertEquals(herb, felineSpy.eatMeat());
    }

    @Test
    @Tag("Positive")
    @DisplayName("Проверка корректности вызова метода getFamily() единоразово")
    void testGetFamilyFelineCallVerified() {
        felineMock.getFamily();
        Mockito.verify(felineMock, Mockito.times(1)).getFamily();
    }

    @Test
    @Tag("Positive")
    @DisplayName("Проверка, что Feline это всё-таки кошачьи")
    void testGetFamilyFeline() {
        Feline feline = new Feline();
        assertEquals("Кошачьи", feline.getFamily());
    }
}
