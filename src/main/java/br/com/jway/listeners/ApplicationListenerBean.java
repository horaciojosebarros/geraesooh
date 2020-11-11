package br.com.jway.listeners;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ApplicationListenerBean implements ApplicationListener {

	@Override
	public void onApplicationEvent(final ApplicationEvent event) {
		if (event instanceof ContextRefreshedEvent) {
			final ApplicationContext applicationContext = ((ContextRefreshedEvent) event)
					.getApplicationContext();

		}
	}
}