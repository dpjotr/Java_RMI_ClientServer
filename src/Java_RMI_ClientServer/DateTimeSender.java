package Java_RMI_ClientServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeSender extends UnicastRemoteObject implements IDateTime {
    private Date date;
    private final RMIServer launcher;
    DateTimeSender(RMIServer s) throws RemoteException {
        super();
        launcher=s;
    }

    public void setCloseServer(boolean closeServer) throws RemoteException, NotBoundException {
        if(closeServer){
            launcher.close();
        }
    }

    @Override
    public String getDate() throws RemoteException {
        date=new Date();
        DateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(date);
    }

    @Override
    public String getTime() throws RemoteException {
        date=new Date();
        DateFormat dateFormat=new SimpleDateFormat("hh:mm_ss");
        return dateFormat.format(date);
    }

    @Override
    public boolean stop() throws RemoteException {
        try{
            setCloseServer(true);
        }
        catch (NotBoundException e){e.getMessage();}

        return true;
    }
}
