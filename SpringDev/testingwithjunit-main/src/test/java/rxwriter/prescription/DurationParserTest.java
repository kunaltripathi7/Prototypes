package rxwriter.prescription;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class DurationParserTest {

//    @Test
//    public void parseDurationWithValidUnitAndQuantity() {
////        doSomething();
//        assertEquals(14, DurationParser.parseDays("2 Weeks"));
//        assertEquals(30, DurationParser.parseDays("1 Month"));
//    }
//

    @ParameterizedTest
    @CsvSource({"1 month, 30", "2 weeks, 14", "once, 1"})
    public void parseDurationWithValidUnitAndQuantity(String durationQuantity, int expectedDays) {
//        doSomething();
        assertEquals(expectedDays, DurationParser.parseDays(durationQuantity));
    }

    private void doSomething() {
        System.out.println("Do Something");
    }
}