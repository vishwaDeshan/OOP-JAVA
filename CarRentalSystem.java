import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem(){
        cars=new ArrayList<>();
        customers=new ArrayList<>();
        rentals=new ArrayList<>();
    }

    public void addCar(Car car){
        cars.add(car);
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void rentCar(Car car,Customer customer, int days){
        if(car.isAvailable()){
            car.rent();
            rentals.add(new Rental(car,customer,days));
        }else {
            System.out.println("Car is not available for rent");
        }
    }

    public void retunCar(Car car){
        car.returnCar();
        Rental rentalToRemove=null;
        for (Rental rental:rentals){
            if(rental.getCar()==car){
                rentalToRemove=rental;
                break;
            }
        }
        if(rentalToRemove!=null){
            rentals.remove(rentalToRemove);
            System.out.println("Car returned successfully.");
        }else{
            System.out.println("Car was not found");
        }
    }

    public void menu(){
        Scanner scanner=new Scanner(System.in);

        while (true){
            System.out.println("******** Car Rental System *********");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");

            int choice=scanner.nextInt ();
            scanner.nextLine();

            if(choice==1){
                System.out.println("\n ** Rent a Car **");
                System.out.println("Enter your name");
                String customerName=scanner.nextLine();

                System.out.println("\nAvailable Cars:");
                for(Car car: cars){
                    if(car.isAvailable()){
                        System.out.println(car.getCarId()+" - "+ car.getBrand()+" "+car.getModel());
                    }
                }
                System.out.println("\nEnetr the car ID yu want to rent");
                String carId=scanner.nextLine();

                System.out.println("Enter the number of days for rental");
                int rentalDays=scanner.nextInt();

                Customer newCustomer=new Customer("CUS"+(customers.size()+1),customerName);
                addCustomer(newCustomer);

                Car selectedCar=null;
                for(Car car:cars){
                    if(car.getCarId().equals(carId) && car.isAvailable()){
                        selectedCar=car;
                        break;
                    }
                }
                if (selectedCar!=null){
                    double totalPrice= selectedCar.calculatePrice(rentalDays);
                    System.out.println("\n**** Rental Information ****");
                    System.out.println("Customer ID: "+newCustomer.getCustomerId());
                    System.out.println("Customer Name: "+newCustomer.getName());
                    System.out.println("Car: "+ selectedCar.getBrand() + " "+selectedCar.getModel());
                    System.out.println("Rental Days: "+rentalDays);
                    System.out.println("Total Price: Rs. "+ totalPrice);

                    System.out.println("\nConfirm rental(Y/N)");
                    String confirm=scanner.nextLine();

                    if(confirm.equalsIgnoreCase("y")){
                        rentCar(selectedCar,newCustomer,rentalDays);
                        System.out.println("\nCar rented successfully");
                    }else{
                        System.out.println("\nrental is canceled");
                    }
                }

                }else if(choice==2) {
                System.out.println("\n***** Return the car *****");
                System.out.println("Enter the car ID you want to return");
                String carID= scanner.nextLine();

                Car carToreturn=null;
                for (Car car:cars){
                    if(car.getCarId().equals(carID) && !car.isAvailable()){
                        carToreturn=car;
                        break;
                    }
                }
                if(carToreturn!=null){
                    Customer customer=null;
                    for (Rental rental:rentals){
                        if(rental.getCar()==carToreturn){
                            customer=rental.getCustomer();
                        }
                    }
                    if(customer!=null){
                        retunCar(carToreturn);
                        System.out.println("Car returned successfully by "+customer.getName());
                    }else{
                        System.out.println("Invalid car ID or Car is not returned");
                    }
                }
                System.out.println("\nThank you for using the car rental system!");
            }
            else if (choice==3) {
                break;
            }else {
                System.out.println("Invalid choice.Please enter a valid option");
            }
        }
    }
}
