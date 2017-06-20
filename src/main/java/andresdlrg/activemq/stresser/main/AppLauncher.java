package andresdlrg.activemq.stresser.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import andresdlrg.activemq.stresser.sender.ActivemqStresserSender;

public class AppLauncher {

	public static void main(String[] args) throws Exception {
		
		Logger log = LoggerFactory.getLogger(AppLauncher.class);

		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");

		ActivemqStresserSender sender = ctx.getBean("sender", ActivemqStresserSender.class);

		
		sender.startSending();
		
		System.out.println("El programa a terminado");
		
		((ConfigurableApplicationContext)ctx).close();
	}

}
