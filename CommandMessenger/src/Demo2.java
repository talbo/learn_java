public class Demo2 {

    static public void main(String[] args) {

        CommandMessenger cm = new CommandMessenger(1);

        try {
            while(true) {
                Thread.sleep(7000);
                System.out.println("Server answer: " + cm.sendMessage("Demo2 - msg from client"));
            }
        } catch (InterruptedException e) {}

    }

}
