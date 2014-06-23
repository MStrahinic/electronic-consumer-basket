/*
 * HibernateDBBR.java
 *
 * Created on February 22, 2007, 1:30 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package db.hibernateUtility;


import org.hibernate.*;
import org.hibernate.cfg.*;
//simport sun.security.mscapi.KeyStore.ROOT;
/**
 *
 * @author ilijaa
 */
public class HibernateUtility {
    private static final SessionFactory sessionFactory;
    
    static {
        try{
            // Kreira SessionFactory na osnovu hibernate.cfg.xml
            sessionFactory=new Configuration().configure().buildSessionFactory();
        }catch(Throwable ex){
            System.err.println("Inicijalno kreiranje SessionFactory nije uspjelo! " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    
    /*public static void konekcija(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hibernatetest?user=root&password=root");
        } catch (java.lang.InstantiationException ex) {
            Logger.getLogger(HibernateDBBR.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(HibernateDBBR.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HibernateDBBR.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HibernateDBBR.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }*/
    
    
}
