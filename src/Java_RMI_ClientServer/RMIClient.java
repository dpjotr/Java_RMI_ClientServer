package Java_RMI_ClientServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    private final IDateTime IDateTimeSender;

    public RMIClient() throws RemoteException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry(1099);
        IDateTimeSender = (IDateTime) registry.lookup("dateTimeSender");
    }

    private void getDate() throws RemoteException {
    System.out.println(IDateTimeSender.getDate());
    }
    private void getTime() throws RemoteException {
    System.out.println(IDateTimeSender.getTime());
    }
    private void  stopServer() throws RemoteException {
        IDateTimeSender.stop();
    }

    static public void main(String[]args){
        try {
            RMIClient client=new RMIClient();
            client.getDate();
            client.getTime();
            client.stopServer();
        }
        catch (RemoteException|NotBoundException e){
            e.getMessage();
        }
    }
}
