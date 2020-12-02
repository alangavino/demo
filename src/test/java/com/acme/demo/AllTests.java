package com.acme.demo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import com.acme.demo.bo.DemoBOTest;
import com.acme.demo.controller.DemoControllerTest;
import com.acme.demo.dao.ConnextionDAOTest;
@RunWith(Suite.class)
@SuiteClasses( {DemoBOTest.class,DemoControllerTest.class,ConnextionDAOTest.class })
public class AllTests {

}
