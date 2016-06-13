package vn.edu.fu.veazy.core.model;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
* Unit test for ExModel model.
*/
public class ExModelTest extends TestCase {

   public void testApp() {
       SessionFactory sessionFactory = new Configuration().configure()
               .buildSessionFactory();
       Session session = sessionFactory.openSession();
       session.beginTransaction();

       ExModel user = new ExModel("firstuser");
       session.save(user);

       session.getTransaction().commit();
       session.close();
   }
}
