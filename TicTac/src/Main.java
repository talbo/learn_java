public class Main {

    public static void main(String[] args) {

        Battlefield battlefield = new Battlefield();
        Viewer viewer = new Viewer(battlefield);
        Controller controller = new Controller(battlefield, viewer);

        /*

        Tic-Tac game

        Battlefield - [model]
            - хранит состояние игрового поля

        Viewer - [view]
            - отображает поле в консоли

        Controller - [controller]
            - запрашивает ходы
            - объявляет победу/конец игры
            - играет за робота

         */

    }

}
