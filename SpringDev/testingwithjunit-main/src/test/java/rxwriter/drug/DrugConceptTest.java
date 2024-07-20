package rxwriter.drug;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrugConceptTest {

    public final static DrugConcept TEST_CONCEPT = new DrugConcept(new DrugClassification[]{
            DrugClassification.ANTIANXIETY,
            DrugClassification.ANALGESICS_NARCOTIC,
            DrugClassification.NARCOTIC_ANTIHISTAMINE});


    @Test
    public void drugBelongsInConceptIfOneClassMatches() {
        DispensableDrug drug = new DispensableDrug("adrug", new DrugClassification[] {
                DrugClassification.ANTIANXIETY, DrugClassification.ANALGESICS_NARCOTIC, DrugClassification.NARCOTIC_ANTIHISTAMINE
        }, false);
        assertTrue(TEST_CONCEPT.isDrugInConcept(drug));
    }

    @Test
    void drugNotInConceptIfNoClassMatches() {
        DispensableDrug drug = new DispensableDrug("bdrug", new DrugClassification[]{
                DrugClassification.NASAL_CORTICOSTEROIDS, DrugClassification.ANALGESIC
        }, false);
        assertFalse(TEST_CONCEPT.isDrugInConcept(drug));

    }


}