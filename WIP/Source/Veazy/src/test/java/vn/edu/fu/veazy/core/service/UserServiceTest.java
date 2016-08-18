/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Hoang Linh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = { "classpath:testContext.xml" } )
public class UserServiceTest {
    
    @Autowired
    private ExamService examService;
    
    @Before
    public void setUp() {
        //need initialed data
    }

    @After
    public void tearDown() {
        try {
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void testFindLearnerExams() throws Exception {
    }
    
}
