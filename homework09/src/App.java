import model.*;

public class App {
    public static void main(String[] args) {

        Car car = new Car("Renault", "Megane 2", 2008, 113, 10, 80, 100);
        PerformanceCar pCar = new PerformanceCar("Ford", "Focus", 2009, 115, 3, 100, 80);
        ShowCar sCar = new ShowCar("Opel", "Astra", 2008, 120, 2, 90, 70);
        sCar.setStars(5);

        DragRace drag = new DragRace(400, "Экстра гонка", 10000);
        drag.addCar(pCar);
        drag.addCar(sCar);

        Garage garage = new Garage(new Car[] {car, pCar, sCar});
        System.out.println(garage);

        System.out.println("Drag Race: " + drag);
        System.out.println("PerformanceCar: " + pCar);
        System.out.println("ShowCar: " + sCar);

        }
}