package ac.cr.ucr.ci1320;

public class ReadThread implements Runnable{

    private Server server;
    public ReadThread(Server server){
        this.server = server;
    }

    @Override
    public void run(){
        this.server.startServer();
    }

}
