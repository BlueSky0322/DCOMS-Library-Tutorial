package RMIConnections;

import Class.User;
import java.rmi.*;

/**
 *
 * @author RyanNg
 */
public interface Interface extends Remote{
    public void login(User user) throws Exception;
}
