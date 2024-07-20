package rxwriter.drug;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.JRE;
import rxwriter.drug.database.DrugRecord;
import rxwriter.drug.database.DrugSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DrugService should")
class DrugServiceTest implements DrugSource {

    private DrugService drugService;

    @BeforeEach
    void setUp() {
        drugService = new DrugService(this);
    }

    @DisplayName("return the records from database in sorted order.")
    @EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_14)
    @Test
    void drugsAreReturnedSorted() {

        List<DispensableDrug> drugs = drugService.findDrugsStartingWith("as");
        assertNotNull(drugs);
        assertEquals(2, drugs.size());
        assertEquals( "asmanex",drugs.get(0).drugName());
        assertEquals("aspirin", drugs.get(1).drugName());
    }


    @Nested
    @DisplayName("throws an illegal arg excep")
    class ThrowsExceptionTests {

        @DisplayName("when passed a blank string")
    @Test
    @Tag("database")
    void throwsExceptionOnBlankStartsWith() {

        Exception thrown = assertThrows(IllegalArgumentException.class, () -> drugService.findDrugsStartingWith(" "));
        System.out.println(thrown.getMessage());
    }

        @Test
        @Tag("database")
        void throwsExceptionOnEmptyStartsWith() {

            Exception thrown = assertThrows(IllegalArgumentException.class, () -> drugService.findDrugsStartingWith(""));
            System.out.println(thrown.getMessage());
        }
    }

    @Test
    void setsUpPropertiesCorrectly() {

        List<DispensableDrug> foundDrugs = drugService.findDrugsStartingWith("aspirin");
        DrugClassification[] expectedClassfications = new DrugClassification[]{
                DrugClassification.ANALGESIC, DrugClassification.PLATELET_AGGREGATION_INHIBITORS
        };
        DispensableDrug drug = foundDrugs.get(0);
        assertAll( "Dispensable Drug properties",
                () -> assertEquals("aspirin", drug.drugName()),
                () -> assertFalse(drug.isControlled()),
                () -> assertArrayEquals(expectedClassfications, drug.drugClassifications()),
                () -> assertEquals(1, foundDrugs.size())

        );
    }

    @Override
    public List<DrugRecord> findDrugsStartingWith(String startingString) {
        List<DrugRecord> records = new ArrayList<>();
        if (startingString.equals("as")) {
            records.add(new DrugRecord("asmanex", new int[] {301}, 0));
            records.add(new DrugRecord("aspirin", new int[] {3645, 3530}, 0));
        }
        else if (startingString.equals("aspirin")) {
            records.add(new DrugRecord("aspirin", new int[] {3645, 3530}, 0));
        }
        return records;
    }
}