package andresdlrg.activemq.stresser.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import andresdlrg.activemq.stresser.sender.ActivemqStresserSender;

public class AppLauncher {
	
	private static Logger log = LoggerFactory.getLogger(AppLauncher.class);
	
	private AppLauncher() {
	}

	public static void main(String[] args) {
		log.info("ActiveMQ-stresser starting...");
		
		String configFile = System.getProperty("configFile", "classpath:config.properties");
		System.setProperty("configFile", configFile);

		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");

		ActivemqStresserSender sender = ctx.getBean("sender", ActivemqStresserSender.class);
		
		try {
			sender.startSending();
		} catch (Exception e) {
			log.error("Error while executing the program", e);
		}
		
		((ConfigurableApplicationContext)ctx).close();
		log.info("ActiveMQ-stresser closing...");
		System.exit(1);
	}

}
