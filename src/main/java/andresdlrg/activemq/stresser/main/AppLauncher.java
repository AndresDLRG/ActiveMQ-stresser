package andresdlrg.activemq.stresser.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import andresdlrg.activemq.stresser.sender.ActivemqStresserSender;

public class AppLauncher {
	
	private static Logger log = LoggerFactory.getLogger(AppLauncher.class);

	public static void main(String[] args) throws Exception {
		log.info("ActiveMQ-stresser starting...");

		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");

		ActivemqStresserSender sender = ctx.getBean("sender", ActivemqStresserSender.class);
		
		sender.startSending();
		
		((ConfigurableApplicationContext)ctx).close();
		log.info("ActiveMQ-stresser closing...");
		System.exit(1);
	}

}
