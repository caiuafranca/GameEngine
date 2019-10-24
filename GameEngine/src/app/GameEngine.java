package app;

public class GameEngine implements Runnable {

    public boolean isRunning;
    private Thread thread;

    public GameEngine(){
        
    }

    public static void main(String[] args) throws Exception {
        GameEngine game = new GameEngine();
        game.start();
    }

    public synchronized void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void tick() {
        //Atualizar os Jogos
        System.out.println("Tick");
    }

    public void render() {
        //Renderizar os Jogos
        System.out.println("Render");
    }

    @Override
    public void run() {
        while (isRunning) {
            tick();
            render();
            
            /*
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            */
        }

    }
}