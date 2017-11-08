package ac.cr.ucr.ci1320.Threads;

import ac.cr.ucr.ci1320.Connection.Server;

public class ReadThread implements Runnable{

    private Server server;
    public ReadThread(Server server){
        this.server = server;
    }

    @Override
    public void run(){
        try {
            this.server.startServer();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
