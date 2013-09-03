/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.dssforut.util.DatabaseInfoEnum;
import com.dssforut.util.EnvInfoEnum;
import com.dssforut.util.PropsReader;
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
         DatabaseInfoEnum property = DatabaseInfoEnum.db_user;
         
         actAssert(PropsReader.ConfigTarget.database, expected, property);
     }
     
     @Test
     public void getDbName() {
         String expected = "dssforut_db";
         DatabaseInfoEnum property = DatabaseInfoEnum.db_name;
         
         actAssert(PropsReader.ConfigTarget.database, expected, property);
     }
     
     @Test
     public void getDbPassword() {
         String expected = "dssforut_root";
         DatabaseInfoEnum property = DatabaseInfoEnum.db_password;
         
         actAssert(PropsReader.ConfigTarget.database, expected, property);
     }
     
     @Test
     public void getDbHost() {
         String expected = "ec2-23-21-211-172.compute-1.amazonaws.com";
         DatabaseInfoEnum property = DatabaseInfoEnum.db_host;
         
         actAssert(PropsReader.ConfigTarget.database, expected, property);
     }
     
     @Test
     public void getEnvValue(){
         
         String expected = "false";
         EnvInfoEnum property = EnvInfoEnum.real_env;
         
         actAssert(PropsReader.ConfigTarget.environment, expected, property);
     }
     
     @Test
     public void getEnvFrecuency(){
         
         String expected = "10000";
         EnvInfoEnum property = EnvInfoEnum.disconnected_env_frecuency;
         
         actAssert(PropsReader.ConfigTarget.environment, expected, property);
     }
     
     @Test
     public void getEnvPort(){
         
         String expected = "COM5";
         EnvInfoEnum property = EnvInfoEnum.usb_com_port;
         
         actAssert(PropsReader.ConfigTarget.environment, expected, property);
     }
     
     public void actAssert( PropsReader.ConfigTarget value, String expected, DatabaseInfoEnum property ){
         
         //ARRANGE
         PropsReader reader = new PropsReader();
         //ACT
         String actual = reader.getConfigProperty(value, property);
         //ASSERT
         Assert.assertEquals( expected, actual);
         
     }
     
     private void actAssert( PropsReader.ConfigTarget value, String expected, EnvInfoEnum property ){
         
         //ARRANGE
         PropsReader reader = new PropsReader();
         //ACT
         String actual = reader.getConfigProperty(value, property);
         //ASSERT
         Assert.assertEquals( expected, actual);
         
     }
}