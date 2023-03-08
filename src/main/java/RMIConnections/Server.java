package RMIConnections;

import Class.User;
import Class.utils.dbConn;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Server extends UnicastRemoteObject implements Interface {

    public Server() throws RemoteException {
        super();
    }

    @Override
    public void login(User user) throws Exception {
        String query = """
                       SELECT username, password
                       FROM DEMO
                       WHERE username=?
                       """;
        PreparedStatement ps = dbConn.preparedStatement(query);
        ps.setString(1, user.getUsername());

        ResultSet result = ps.executeQuery();
        // if user not found
        if (!result.next()) {
            throw new Exception("User not found! Login Failed!");
        }


    }
}
