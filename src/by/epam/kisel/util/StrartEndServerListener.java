package by.epam.kisel.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.kisel.util.parameterConstants.LogMessage;

/**
 * Application Lifecycle Listener implementation class StrartEndServerListener
 *
 */
@WebListener
public class StrartEndServerListener implements ServletContextListener, HttpSessionListener, ServletRequestListener {

	
	private static Logger logger = LogManager.getLogger();
 
    public StrartEndServerListener() {
        
    }


    public void sessionCreated(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent arg0)  { 
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
