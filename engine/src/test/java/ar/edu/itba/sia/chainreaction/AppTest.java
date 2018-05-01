package ar.edu.itba.sia.chainreaction;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.IOException;
import java.util.stream.IntStream;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void test5Through10()
    {
        IntStream.range(5, 11).forEach((size) -> {
            IntStream.range(1, 4).forEach(iter -> {
                try {
                    String f = size + "x" + size + "/" +size + "x" + size + "test" + iter + ".txt";
                    System.out.println("\""+ f + "\"");
                    App.testFile("./test_problems/" + f);
                    System.out.println("////////////\n");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            });
        });
    }

    @Test
    public void test20x20()
    {
        int size = 9;
        IntStream.range(5, 11).forEach((shape) -> {
            IntStream.range(1, 4).forEach(iter -> {
                try {
                    String f = "all" + size + "x" + size + "/" +size + "x" + size + "["+shape+","+shape+ "]test" + iter + ".txt";
                    System.out.println("\""+ f + "\"");
                    App.testFile("./test_problems/" + f);
                    System.out.println("////////////\n");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            });
        });
    }

    @Test
    public void testall5x5() {
        IntStream.range(1, 100).forEach(iter -> {
            try {
                int size = 5, shape = 4, color=7;
                String f = "all" +size + "x" + size + "/" +size + "x" + size + "["+shape+","+color+ "]test" + iter + ".txt";
                System.out.println("\""+ f + "\"");
                App.testFile("./test_problems/" + f);
                System.out.println("////////////\n");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        });
    }
}
