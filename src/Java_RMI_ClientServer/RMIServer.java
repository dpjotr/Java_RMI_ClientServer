package Java_RMI_ClientServer;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class RMIServer {
    Registry registry;
    String objectName="dateTimeSender";
    DateTimeSender dtSender;
    static public void main(String[]args){
            new RMIServer().run();
    }
    void close() throws RemoteException, NotBoundException {
        registry.unbind(objectName);
        UnicastRemoteObject.unexportObject(dtSender, true);
        System.out.println("Server is closed");
    }
    private void run(){
        try {
            dtSender=new DateTimeSender(this);
            registry= LocateRegistry.createRegistry(1099);
            registry.bind(objectName, dtSender);
            System.out.println("Server started");
        }
        catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
