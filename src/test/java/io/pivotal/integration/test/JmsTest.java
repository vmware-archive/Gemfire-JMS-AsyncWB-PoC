package io.pivotal.integration.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/context/spring-context.xml")
//@SpringApplicationConfiguration(classes = AttisApplication.class)
public class JmsTest {

	@Resource(name="jmsTemplate")
	private JmsTemplate jmsTemplate;

	@Test
	public void testJms() {

		jmsTemplate.convertAndSend( "test.queue1", "{\"id\":\"14\",\"sym\":\"APPL\",\"qnt\":\"10\",\"amt\":\"1000.00\"}");

	}

}