package ac.cr.ucr.ci1320.Threads;

import ac.cr.ucr.ci1320.Conection.Server;

public class DispatcherThread implements Runnable {
    private Server server;

    public DispatcherThread(Server server){
        this.server = server;
    }

    @Override
    public void run(){
        try {
            this.server.startServerDispatcher();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

