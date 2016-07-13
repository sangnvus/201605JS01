package vn.edu.fu.veazy.core.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

/**
* Unit test for ExModel model.
*/

@WebAppConfiguration
@ContextConfiguration("appContext.xml")
public class ExModelTest {
    
    @Autowired
    private SessionFactory sessionFactory;
//    
//    @Before
//    public void setUp() {
//        sessionFactory = new Configuration().configure()
//                .buildSessionFactory();
//    }

    @Test
    public void testExModel() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        ExModel user = new ExModel("firstuser");
        session.save(user);

        session.getTransaction().commit();
        session.close();
    }
}
