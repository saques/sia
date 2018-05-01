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
    public void shouldAnswerWithTrue()
    {
        IntStream.range(5, 11).forEach((size) -> {
            IntStream.range(1, 4).forEach(iter -> {
                try {
                    String f = size + "x" + size + "/" +size + "x" + size + "test" + iter + ".txt";
                    System.out.println("\""+ f + "\"");
                    App.testFile("./test_problems/" + f);
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
}
