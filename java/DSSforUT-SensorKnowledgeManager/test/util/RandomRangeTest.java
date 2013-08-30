/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import junit.framework.Assert;
import org.junit.Test;
import com.dssforut.util.RandomRange;

/**
 *
 * @author Sebastian
 */
public class RandomRangeTest {

    @Test
    public void getRandomNumberInRange() {
        //Arrange
        int min = 1;
        int max = 3;
        //Act
        int randomNumber = RandomRange.generateRandomRange(min, max);
        //Assert
        Assert.assertTrue("Number is not between the range", randomNumber >= min && randomNumber <= max);
    }
}