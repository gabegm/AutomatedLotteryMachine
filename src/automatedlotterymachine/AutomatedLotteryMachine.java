/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package automatedlotterymachine;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabegm
 */
public class AutomatedLotteryMachine {
    private final Lotto lotto;
    private final Jackpot jackpot;
    private final National national;
    private final MessageDigest sha;

    /**
     * Constructor for the main class
     * @throws NoSuchAlgorithmException 
     */
    public AutomatedLotteryMachine() throws NoSuchAlgorithmException {
        this.lotto = new Lotto();
        this.jackpot = new Jackpot();
        this.national = new National();
        this.sha = MessageDigest.getInstance("SHA-256");
    }

    /**
     * @param args the command line arguments
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        AutomatedLotteryMachine alm = new AutomatedLotteryMachine();
        if(alm.checkFileExists("password.txt")) { 
            if(alm.checkFileExists("lottoTicket.txt")) { 
                alm.lotto.retrieveSavedData();
            }
            if(alm.checkFileExists("jackpotTicket.txt")) { 
                alm.jackpot.retrieveSavedData();
            }
            if(alm.checkFileExists("nationalTicket.txt")) { 
                alm.national.retrieveSavedData();
            }
            alm.loginScreen();
        }else {
            alm.setup();
        }
    }
    
    /**
     * This is the main menu for both the client and employee
     * @throws IOException 
     */
    public void loginScreen() throws IOException {        
        boolean showMenu = true;
        while(showMenu){
            System.out.print("Welcome to the login screen\n"
                + "Please enter C for client, E for employee, H for help, or Q to quit: ");
            Scanner s3 = new Scanner(System.in);

            while (!s3.hasNext("[CcEeHhQq]")) {
                System.out.println("Try again");
                s3.next();
            }

            char ch = s3.next().charAt(0);
            if(ch == 'E' || ch == 'e') {
                employeeScreen();
            }else if(ch == 'C' || ch == 'c') {
                clientScreen();
            }else if(ch == 'H' || ch == 'h') {
                launchHelpFile();
                showMenu = true;
            }else if(ch == 'Q' || ch == 'q') {
                deletePasswordFile();
                System.exit(0);
            }
        }
    }
    
    /**
     * This is the main menu for the employee
     * @throws IOException 
     */
    public void employeeScreen() throws IOException {
        boolean showMenu = true;
        String shaPassword;
        String employeePassword;
        String password;
        sha.reset();
        while(showMenu){
            System.out.println("Welcome to the employee screen");

            do {
                System.out.print("Please enter the password: ");
                Scanner s1 = new Scanner (System.in);
                password = s1.next();
                sha.update(password.getBytes(), 0, password.length());
                shaPassword = new BigInteger(1, sha.digest()).toString(16);
                BufferedReader br = new BufferedReader(new FileReader("password.txt"));
                employeePassword = br.readLine();
            }while(!shaPassword.equals(employeePassword));

            System.out.print("There are 3 Lotteries (Lotto, Jackpot and National\n"
                + "Please select one (L, J, N) or E to exit: ");
            Scanner s2 = new Scanner (System.in);
            while(!s2.hasNext("[LlJjNnEe]")) {
                System.out.println("Try again");
                s2.next();
            }
            char choice = s2.next().charAt(0);
            if(choice == 'E' || choice == 'e') {
                break;
            }else if(choice == 'L' || choice == 'l') {
                lotto.draw();
                System.out.println(lotto);
                lotto.getLottoWinner();
                lotto.deleteLottoTickets();
                showMenu = false;
            }
            else if(choice == 'J' || choice == 'j') {
                jackpot.draw();
                System.out.println(jackpot);
                jackpot.getJackpotWinner();
                jackpot.deleteJackpotTickets();
                showMenu = false;
            }else if(choice == 'N' || choice == 'n') {
                national.draw();
                System.out.println(national);
                national.getNationalWinner();
                national.deleteNationalTickets();
                showMenu = false;
            }else{
                System.out.println("Unknown command");
                showMenu = false;
            }
        }
    }
    
    /**
     * This is the main menu for the client
     * @throws IOException 
     */
    public void clientScreen() throws IOException {
        boolean showMenu = true;
        
        while(showMenu){
            System.out.print("Welcome to the client screen\n" 
                + "There are 3 Lotteries (Lotto, Jackpot and National "
                + "Please select one (L, J, N) or E to exit: ");

            Scanner s = new Scanner (System.in);

            while(!s.hasNext("[LlJjNnEe]")) {
                System.out.println("Try again");
                s.next();
            }

            char choice2 = s.next().charAt(0);

            if(choice2 == 'E' || choice2 == 'e') {
                break;
            }else if(choice2 == 'L' || choice2 == 'l') {
                lotto();
            }
            else if(choice2 == 'J' || choice2 == 'j') {
                jackPot();
            }
            else if(choice2 == 'N' || choice2 == 'n') {
                national();
            }
        }
    }
    
    /**
     * Creates the lotto ticket
     * @throws IOException 
     */
    public void lotto() throws IOException {
        boolean showMenu = true;
        List<Integer> numbers = new ArrayList<>();
        int limit = 0;
        int lottoType = 0;
        int amountOfNumbers;
        int numberInput;
        String input;
        String[] inputSplit;
        
        while(showMenu){
            System.out.print("Please Enter amount of money: ");
            Scanner scannerMoney = new Scanner(System.in);
            double money = scannerMoney.nextDouble();
            System.out.print("There are 3 Types (Prima, Ambo and Terno)\n" 
                + "Please select one (P, A, T) or E to exit: ");
            Scanner s5 = new Scanner(System.in);

            while(!s5.hasNext("[PpAaTtEe]")) {
                System.out.println("Try again");
                s5.next();
            }

            char type = s5.next().charAt(0);

            if (type == 'E' || type == 'e') {
                break;
            }
            else if(type == 'P' || type == 'p') {
                lottoType = 1;
            }
            else if(type == 'A' || type == 'a') {
                lottoType = 2;
            }
            else if(type == 'T' || type == 't') {
                lottoType = 3;
            }

            System.out.print("Would you like to choose your numbers? [Y/N]: ");
            Scanner s7 = new Scanner(System.in);

            while(!s7.hasNext("[YyNn]")) {
                System.out.println("Try again");
                s7.next();
            }

            char chooseNumber = s7.next().charAt(0);
            if (lottoType == 1) {
                limit = 1;
            }
            else if (lottoType == 2 || lottoType == 3) {
                limit = 5;
            }
            Scanner numbersScanner = new Scanner(System.in);      
            if(chooseNumber == 'y' || chooseNumber == 'Y') {
                do {
                    if (lottoType == 1) {
                        System.out.print("Choose a number between 1 and 90: ");
                    }
                    else if (lottoType == 2 || lottoType == 3) {
                        System.out.print("Choose 5 numbers between 1 and 90 seperated by spaces: ");
                    }

                    /*while(!numbersScanner.hasNext("[0-9]")) {
                        System.out.println("Try again");
                        numbersScanner.next();
                    }*/

                    input = numbersScanner.nextLine();
                    inputSplit = input.split(" ");
                    amountOfNumbers = inputSplit.length;     

                    if((amountOfNumbers > limit) || (amountOfNumbers < limit)) {
                        System.out.println("Try again");
                        amountOfNumbers = 0;
                        numbers.clear();
                    }

                    for (int x = 0; x < amountOfNumbers; x++) {
                        try {
                            numberInput = Integer.parseInt(inputSplit[x]);
                            numbers.add(numberInput);
                        }catch(NumberFormatException e) {
                            System.out.println("You can only enter numbers!");
                            amountOfNumbers = 0;
                            numbers.clear(); 
                        }
                    }
                }while(!(amountOfNumbers == limit));
            }else if(chooseNumber == 'n' || chooseNumber == 'N') {
                numbers.clear();
                int rand;
                for (int x = 0; x < limit; x++) {    
                    rand = Lottery.randInt(1, 90);
                    while(numbers.contains(rand)){
                        rand = Lottery.randInt(1, 90);
                    }
                    numbers.add(rand); 
                }
            }      
            LottoTicket lottoTicket = new LottoTicket(lottoType, numbers);
            lottoTicket.setTicketValue(money);
            lottoTicket.calculateWinValue();
            lotto.addLottoTicket(lottoTicket); 
            lotto.writeLottoTicket(lottoTicket);
            System.out.println(lottoTicket);
            showMenu = false;
        }
    }
    
    /**
     * Creates the jackpot ticket
     * @throws IOException 
     */
    public void jackPot() throws IOException {     
        boolean showMenu = true;
        List<Integer> numbers = new ArrayList<>();
        int limit = 5;
        numbers.clear();
        int amountOfNumbers;
        int numberInput;
        String input;
        String[] inputSplit;
        
        while(showMenu){
            System.out.print("Would you like to choose your numbers? [Y/N]: ");
            Scanner s7 = new Scanner(System.in);

            while(!s7.hasNext("[YyNn]")) {
                System.out.println("Try again");
                s7.next();
            }

            char chooseNumber = s7.next().charAt(0);
            if(chooseNumber == 'y' || chooseNumber == 'Y') { 
                do {        
                    System.out.print("Choose 5 numbers between 1 and 35 seperated by spaces: ");
                    Scanner jackpotScanner = new Scanner(System.in);

                    /*while(!jackpotScanner.hasNextLine()) {
                        System.out.println("Try again");
                        jackpotScanner.next();
                    }*/

                    input = jackpotScanner.nextLine();
                    inputSplit = input.split(" ");
                    amountOfNumbers = inputSplit.length; 

                    if(amountOfNumbers > limit || amountOfNumbers < limit) {
                        System.out.println("Try again");
                        amountOfNumbers = 0;
                        numbers.clear();
                    }

                    for (int x = 0; x < amountOfNumbers; x++) {
                        try {
                            numberInput = Integer.parseInt(inputSplit[x]);
                            numbers.add(numberInput);
                        }catch(NumberFormatException e) {
                            System.out.println("You can only enter numbers!");
                            amountOfNumbers = 0;
                            numbers.clear(); 
                        }
                    }
                }while(!(amountOfNumbers == limit));
            }
            else if(chooseNumber == 'n' || chooseNumber == 'N') {
                numbers.clear();
                int rand;
                for (int x = 1; x <= 5; x++) {
                    rand = Lottery.randInt(1, 35);
                    while(numbers.contains(rand)){
                        rand = Lottery.randInt(1, 35);
                    }
                    numbers.add(rand); 
                }
            }
            JackpotTicket jackpotTicket = new JackpotTicket(numbers);
            jackpot.addJackpotTicket(jackpotTicket);
            jackpot.writeJackpotTicket(jackpotTicket);
            System.out.println(jackpotTicket);
            showMenu = false;
        }
    }
    /**
     * Creates the national ticket
     * @throws IOException 
     */
    public void national() throws IOException {        
        boolean showMenu = true;
        List<Integer> numbers = new ArrayList<>();
        int limit = 10;
        numbers.clear();
        int amountOfNumbers;
        int numberInput;
        String input;
        String[] inputSplit;
        
        while(showMenu){
            System.out.print("Would you like to choose your numbers? [Y/N]: ");
            Scanner s7 = new Scanner(System.in);

            while (!s7.hasNext("[YyNn]")) {
                System.out.println("Try again");
                s7.next();
            }

            char chooseNumber = s7.next().charAt(0);
            if(chooseNumber == 'y' || chooseNumber == 'Y') { 
                do {
                    System.out.print("Choose 10 numbers between 1 and 99: ");
                    Scanner nationalScanner = new Scanner(System.in);

                    /*while (!nationalScanner.hasNextLine()) {
                        System.out.println("Try again");
                        nationalScanner.next();
                    }*/

                    input = nationalScanner.nextLine();
                    inputSplit = input.split(" ");
                    amountOfNumbers = inputSplit.length; 

                    if(amountOfNumbers > limit || amountOfNumbers < limit) {
                        System.out.println("Try again");
                        amountOfNumbers = 0;
                        numbers.clear();
                    }

                    for (int x = 0; x < amountOfNumbers; x++) {
                        try {
                            numberInput = Integer.parseInt(inputSplit[x]);
                            numbers.add(numberInput);
                        }catch(NumberFormatException e) {
                            System.out.println("You can only enter numbers!");
                            amountOfNumbers = 0;
                            numbers.clear(); 
                        } 
                    }
                }while(!(amountOfNumbers == limit));
            }else if(chooseNumber == 'n' || chooseNumber == 'N') {
                numbers.clear();
                int rand;
                for (int x = 1; x <= 10; x++) {
                    rand = Lottery.randInt(1, 99);
                    while(numbers.contains(rand)){
                        rand = Lottery.randInt(1, 99);
                    }
                    numbers.add(rand); 
                }
            }
            NationalTicket nationalTicket = new NationalTicket(numbers);
            national.addNationalTicket(nationalTicket);
            national.writeNationalTicket(nationalTicket); 
            System.out.println(nationalTicket);
            showMenu = false;
        }
    }
    
    /**
     * Set up for first run
     * @throws IOException 
     */
    public void setup() throws IOException {
        System.out.println("Welcome to the Lottery System, this is the first run");
        String password, passwordCheck;
        do {
            System.out.print("Enter Password: ");
            Scanner s1 = new Scanner(System.in);
            password = s1.next();

            System.out.print("Re-Enter Password: ");
            Scanner s2 = new Scanner(System.in);
            passwordCheck = s2.next();

            if(compareString(password, passwordCheck)) {
                sha.update(password.getBytes(), 0, password.length());
                String shaPassword = new BigInteger(1, sha.digest()).toString(16);
                try (PrintWriter out = new PrintWriter("password.txt")) {
                        out.print(shaPassword);    
                        out.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AutomatedLotteryMachine.class.getName()).log(Level.SEVERE, null, ex);
                }
                loginScreen();
            }
            else {
                System.out.println("Passwords do not match!");
            }
        }while(!compareString(password, passwordCheck));
    }
    
    /**
     * Compares two strings and returns true if duplicate
     * @param str1
     * @param str2
     * @return 
     */
    public boolean compareString(String str1, String str2) {
        boolean check = false;
        if(str1 == null ? str2 == null : str1.equals(str2)) {
            check = true;
        }
        return check;
    }
    
    /**
     * Returns true is specified file exists
     * @param filePathString
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public boolean checkFileExists(String filePathString) throws FileNotFoundException, IOException {
        boolean check = false;
        File f = new File(filePathString);
        
        if(f.exists() && !f.isDirectory()) {
            BufferedReader br = new BufferedReader(new FileReader(filePathString));
            if (br.readLine() != null) {            
                check = true;
            }
        }
        return check;
    }
    
    /**
     * Launches the help file in the user's browser
     * @throws IOException 
     */
    public void launchHelpFile() throws IOException {
        String url = "help/index.html";
        File htmlFile = new File(url);
        Desktop.getDesktop().browse(htmlFile.toURI());
    }
    
    /**
     * Deletes the password file when the employee quits the application
     */
    public void deletePasswordFile() {
        try {
            File file = new File("password.txt");
            file.delete();
        }catch(Exception e) {
           System.out.println("File could not be deleted");
        }
    }
}
