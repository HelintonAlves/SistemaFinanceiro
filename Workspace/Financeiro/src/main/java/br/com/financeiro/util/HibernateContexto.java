package br.com.financeiro.util;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class HibernateContexto implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		HibernateUtil.getFabricaDeSessoes().close();
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		HibernateUtil.getFabricaDeSessoes().openSession();
		
	}

}
