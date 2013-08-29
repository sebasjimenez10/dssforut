/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test.Util;

import Util.DatabaseInfoEnum;
import Util.EnvInfoEnum;
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
     * db_something (user, password, name, host)
     */
    
     @Test
     public void getDbUser() {
         String expected = "dssforut_root";
         String property = DatabaseInfoEnum.db_user.name();
         
         actAssert(PropsReader.ConfigTarget.database, expected, property);
     }
     
     @Test
     public void getDbName() {
         String expected = "dssforut_db";
         String property = DatabaseInfoEnum.db_name.name();
         
         actAssert(PropsReader.ConfigTarget.database, expected, property);
     }
     
     @Test
     public void getDbPassword() {
         String expected = "dssforut_root";
         String property = DatabaseInfoEnum.db_password.name();
         
         actAssert(PropsReader.ConfigTarget.database, expected, property);
     }
     
     @Test
     public void getDbHost() {
         String expected = "ec2-23-21-211-172.compute-1.amazonaws.com";
         String property = DatabaseInfoEnum.db_host.name();
         
         actAssert(PropsReader.ConfigTarget.database, expected, property);
     }
     
     @Test
     public void getEnvValue(){
         
         String expected = "false";
         String property = EnvInfoEnum.real_env.name();
         
         actAssert(PropsReader.ConfigTarget.environment, expected, property);
     }
     
     public void actAssert( PropsReader.ConfigTarget value, String expected, String property ){
         
         //ARRANGE
         PropsReader reader = new PropsReader();
         //ACT
         String actual = reader.getConfigProperty(value, property);
         //ASSERT
         Assert.assertEquals( expected, actual);
         
     }
}