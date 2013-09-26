public class Demo1 {

    static public void main(String[] args) {

        CommandMessenger cm = new CommandMessenger(0);

        try {
            while(true) {
                Thread.sleep(5000);
                System.out.println("Client answer: " + cm.sendMessage("Demo1 - msg from server"));
            }
        } catch (InterruptedException e) {}

    }

}
