import org.junit.Test;

import static org.junit.Assert.*;

public class CodingBatTest {

    @Test
    public void monkeyTrouble() {

        assertTrue(new CodingBat().monkeyTrouble(true, true));
        assertTrue(new CodingBat().monkeyTrouble(false, false));
        assertFalse(new CodingBat().monkeyTrouble(false, true));
        assertFalse(new CodingBat().monkeyTrouble(true, false));
    }

    @Test
    public void firstHalf() {
        assertEquals("Woo", new CodingBat().firstHalf("WooHoo"));
        assertEquals("Hello", new CodingBat().firstHalf("HelloThere"));
        assertEquals("abc", new CodingBat().firstHalf("abcdef"));
        assertEquals("a", new CodingBat().firstHalf("ab"));
        assertEquals("", new CodingBat().firstHalf(""));
        assertEquals("01234", new CodingBat().firstHalf("0123456789"));
        assertEquals("kit", new CodingBat().firstHalf("kitten"));


    }

    @Test
    public void countEvens() {

        assertEquals(3, new CodingBat().countEvens(new int[]{2, 1, 2, 3, 4}));
        assertEquals(3, new CodingBat().countEvens(new int[]{2, 2, 0}));
        assertEquals(0, new CodingBat().countEvens(new int[]{1, 3, 5}));
        assertEquals(0, new CodingBat().countEvens(new int[]{}));
        assertEquals(1, new CodingBat().countEvens(new int[]{11, 9, 0, 1}));
        assertEquals(2, new CodingBat().countEvens(new int[]{2, 11, 9, 0}));


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
