import java.util.Arrays;
import java.util.Scanner;

public class CLIappAssignment2{
    private final static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        final String clear = "\033[H\033[2J";
        final String color_Blue = "\033[1;34m";
        final String color_green = "\033[0;32m";
        final String Purple = "\033[0;35m";
        final String reset ="\033[0m";
        final String Yellow = "\033[0;33m";
        final String GREEN_BACKGROUND = "\033[42m";;

        final String Dashboard = "Smart Banking System";
        final String Open_New_Acc = "Open New Account";
        final String Deposit_Money = "Deposit Money";
        final String WithDraw = "Withdraw Money";
        final String Transfer_Money = "Transfer Money";
        final String Acc_Bal = "Check Account Balance";
        final String Del_Acc = "Delete Account";
        
        String screen = Dashboard;
        
        //using matrix arrays in place of 1D arrays.
        String[][] customers = new String [0][];


        main_loop:
        do{
        
            System.out.print(clear);
            String line = String.format("%s%s%s",color_green,"-".repeat(60),reset);
            String title = String.format("%s%s%s%s",color_Blue," ".repeat((60 - screen.length())/2),screen.toUpperCase(),reset);
            System.out.println(line);
            System.out.println(title);
            System.out.println(line);
            
            switch (screen){
                
                case Dashboard:
                    System.out.println("\t[1]. Open New Account");
                    System.out.println("\t[2]. Deposit Money");
                    System.out.println("\t[3]. Withdraw Money");
                    System.out.println("\t[4]. Transfer Money");
                    System.out.println("\t[5]. Check Account Balance");
                    System.out.println("\t[6]. Delete Account");
                    System.out.println("\t[7]. Exit");
                    System.out.print("\tEnter an Option to Continue >> ");
                    int option = scanner.nextInt();
                    scanner.nextLine();

                    switch(option){
                        case 1 : screen = Open_New_Acc;break;
                        case 2 : screen = Deposit_Money;break;
                        case 3 : screen = WithDraw;break;
                        case 4 : screen = Transfer_Money;break;
                        case 5 : screen = Acc_Bal;break;
                        case 6 : screen = Del_Acc;break;
                        case 7 : System.exit(0);break;
                        default : continue;
                    }
                    break;
                
                case Open_New_Acc:
                    
                    
                    int x = 1;
                    int initialDeposit;
                    String id;
                    String accountName;
                    loop:
                    while(true){

                        //generating ids
                        id = String.format("SDB-%05d",x);
                        System.out.printf("New account number => %s%s%s\n",Purple,id,reset);
                        System.out.print("Enter name: ");
                        accountName = scanner.nextLine().strip();
                        
                        if(accountName.isBlank()) {
                            System.out.println("Name cannot be empty. Try again !");
                            continue;
                        }else{
                            for (int i = 0; i < accountName.length() ; i++) {
                                if(!(Character.isLetter(accountName.toLowerCase().charAt(i)) || accountName.charAt(i) == ' ')) {
                                    System.out.print("Invalid Name. Do you want enter a valid name? (Y/N) >> ");
                                    if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue loop;
                                    screen = Dashboard;
                                    break loop;
                                }
                            }
                        }
                        
                        do{
                            System.out.print("Initial Deposit: ");
                            initialDeposit = scanner.nextInt();
                            scanner.nextLine();
                            if(initialDeposit < 5000){
                                System.out.print("Insufficient deposit amount.Do you want deposit a sufficient amount? (Y/N): ");
                                if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                                else screen = Dashboard; 
                                break loop;
                                
                            }else{
                                System.out.printf("Account number %s%s%s of %s\033[1;30m%s%s has been created\n",Yellow,id,reset,GREEN_BACKGROUND,accountName.toUpperCase(),reset);
                                break;
                            }
                        }while(true);


                        //creaion of a temporary matrix
                        String[][] tempCustomers = new String [customers.length+1][3];


                        //copying records from the previous array to the newly created array
                        for (int i = 0; i < customers.length; i++) {
                            tempCustomers[i]=customers[i];
                        }
                        
                        //assigning 
                        tempCustomers[tempCustomers.length-1][0]=id;
                        tempCustomers[tempCustomers.length-1][1]=accountName;
                        tempCustomers[tempCustomers.length-1][2]=initialDeposit + "";


                        //swapping memory locations
                        customers=tempCustomers;


                        System.out.print("Do you want to open another new account? (Y/N) >> ");
                        if(scanner.nextLine().strip().toUpperCase().equals("Y")){
                            x++;
                            continue;
                        }else screen = Dashboard;
                        break;
                        
                    }
            }
        }while(true);
    }
}