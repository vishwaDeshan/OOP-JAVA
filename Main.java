public class Main {
    public static void main(String[] args) {
        CarRentalSystem rentalSystem=new CarRentalSystem();
        Car car1=new Car("C001","Toyota","Camry",5500.00 );
        Car car2=new Car("C002","Audi","Q3",10000.00 );
        Car car3=new Car("C003","Benz","A-class",15000.0 );
        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);

        rentalSystem.menu();
    }
}
