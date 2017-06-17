package andresdlrg.activemq.stresser.main;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import andresdlrg.activemq.stresser.jms.MessageSender;
import andresdlrg.activemq.stresser.model.DataToSendConfiguration;
import andresdlrg.activemq.stresser.model.SampleClass;

public class AppLauncher {

	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException,
			ClassNotFoundException, NoSuchFieldException, SecurityException {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");

		MessageSender mSender = ctx.getBean("messageSender", MessageSender.class);
		DataToSendConfiguration data = ctx.getBean(DataToSendConfiguration.class);

		Class<?> classHandle = Class.forName(data.getClassTypeToSend());
		Object object = classHandle.newInstance();

		Field field = classHandle.getDeclaredField("consecutive");
		field.setAccessible(true);
		field.set(object, 8L);

		//mSender.sendObject((Serializable)object);

		System.out.println(((SampleClass)object).getConsecutive());
		
		Class cl = int.class;
//		Class cl = Class.forName("int");
		System.out.println(cl.);
		
		System.out.println("El programa a terminado");
	}

}
