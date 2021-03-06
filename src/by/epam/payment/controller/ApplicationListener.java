package by.epam.payment.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.payment.util.parameterConstant.LogMessage;

/**
 * Application Lifecycle Listener implementation class StrartEndServerListener
 *
 */
@WebListener
public class ApplicationListener implements ServletContextListener, HttpSessionListener {

	
	private static Logger logger = LogManager.getLogger();
 
    public ApplicationListener() {
        
    }


    public void sessionCreated(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }


    public void contextDestroyed(ServletContextEvent arg0)  { 
        logger.log(Level.INFO, LogMessage.APPLICATION_STOPPED);
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
         logger.log(Level.INFO, LogMessage.APPLICATION_INITIALIZED);
    }
	
}
