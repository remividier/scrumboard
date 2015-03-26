package epsi.scrumboard.dao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import redis.clients.jedis.Jedis;



public class RedisConnection implements ServletContextListener {

	private Jedis jedis; 
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		System.out.println("coucou");
		// TODO Auto-generated method stub
		jedis = new Jedis("localhost");
		
		jedis.set("Coucou", "Bonjour");
		
	}
	
	

}
