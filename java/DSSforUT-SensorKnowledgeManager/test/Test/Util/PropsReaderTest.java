/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test.Util;

import Util.PropsReader;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author Sebastian
 */
public class PropsReaderTest {
    
    /*
     * Properties in property file must be like:
     * 
     * db_something (user, password, name, host)
     */
    
     @Test
     public void getDbUser() {
         //ARRANGE
         String expected = "dssforut_root";
         String property = "db_user";
         
         actAssert(expected, property);
     }
     
     @Test
     public void getDbName() {
         //ARRANGE
         String expected = "dssforut_db";
         String property = "db_name";
         
         actAssert(expected, property);
     }
     
     @Test
     public void getDbPassword() {
         //ARRANGE
         String expected = "dssforut_root";
         String property = "db_password";
         
         actAssert(expected, property);
     }
     
     @Test
     public void getDbHost() {
         //ARRANGE
         String expected = "ec2-23-21-211-172.compute-1.amazonaws.com";
         String property = "db_host";
         
         actAssert(expected, property);
     }
     
     public void actAssert( String expected, String property ){
         
         //ARRANGE
         PropsReader reader = new PropsReader();
         
         //ACT
         String actual = reader.readProp( property );
         
         //ASSERT
         Assert.assertEquals( expected, actual);
         
     }
}