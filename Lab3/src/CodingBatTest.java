import org.junit.Test;

import static org.junit.Assert.*;

public class CodingBatTest {

    @Test
    public void monkeyTrouble() {

        assertTrue(new CodingBat().monkeyTrouble(true,true));
        assertTrue(new CodingBat().monkeyTrouble(false,false));
        assertFalse(new CodingBat().monkeyTrouble(false,true));
        assertFalse(new CodingBat().monkeyTrouble(true,false));
    }

    @Test
    public void firstHalf() {


    }

    @Test
    public void countEvens() {

        assertEquals(2, new CodingBat().countEvens(19));
        assertEquals(11, new CodingBat().countEvens(10));
        assertEquals(0, new CodingBat().countEvens(21));
        assertEquals(8, new CodingBat().countEvens(25));
        assertEquals(18, new CodingBat().countEvens(30));
        assertEquals(21, new CodingBat().countEvens(0));


    }

    @Test
    public void diff21() {
        assertEquals(2, new CodingBat().diff21(19));
        assertEquals(11, new CodingBat().diff21(10));
        assertEquals(0, new CodingBat().diff21(21));
        assertEquals(8, new CodingBat().diff21(25));
        assertEquals(18, new CodingBat().diff21(30));
        assertEquals(21, new CodingBat().diff21(0));


    }
}