import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean quit = false;
        int pin = 0;
        long creditCardNumber = 0;
        Map<Long, Integer> codes = new HashMap<>();

        while (!quit) {
            System.out.println("1. Create an account\n" +
                    "2. Log into account\n" +
                    "0. Exit");
            System.out.print(">");

            int chosenOption = scanner.nextInt();
            scanner.nextLine();

            switch (chosenOption) {
                case 0: {
                    quit = true;
                    break;
                }
                case 1: {
                    System.out.println();

                    // Luhn Algorithm
                    int[] values = new int[16];
                    int[] valuesHelper = new int[values.length];
                    boolean isValid = false;
                    int checksum;
                    String code = "";
                    while (!isValid) {
                        Arrays.fill(values, 0);

                        values[0] = 4;

                        for (int i = 6; i < 15; i++) {
                            values[i] = random.nextInt(10);
                        }

                        System.arraycopy(values, 0, valuesHelper, 0, values.length);
                        for (int i = valuesHelper.length - 2; i >= 0; i -= 2) {
                            valuesHelper[i] *= 2;
                            if (valuesHelper[i] >= 10) {
                                valuesHelper[i] -= 9;
                            }

                        }

                        int sum = 0;

                        for (int value : valuesHelper) {
                            sum += value;
                        }

                        checksum = random.nextInt(10);
                        while ((sum + checksum) % 10 != 0) {
                            checksum = random.nextInt(10);

                            if ((sum + checksum) % 10 == 0) {
                                isValid = true;
                                values[values.length - 1] = checksum;
                            }
                        }


                        // Luhn Algorithm
                    }

                    for (int s : values) {
                        code += String.valueOf(s);
                    }

                    creditCardNumber = Long.parseLong(code);
                    pin = random.nextInt(9999 - 1000) + 1000;


                    codes.put(creditCardNumber, pin);

                    System.out.println("Your card number:");
                    System.out.println(creditCardNumber);
                    System.out.println("Your card PIN:");
                    System.out.println(pin);
                    System.out.println();
                    break;
                }
                case 2: {
                    System.out.println("Enter your card number: ");
                    System.out.print(">");
                    long accountNumberInput = scanner.nextLong();
                    System.out.println("Enter your PIN: ");
                    System.out.print(">");
                    int pinInput = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println();
                    if (creditCardNumber == accountNumberInput && pin == pinInput) {
                        System.out.println("You have successfully logged in! ");
                        boolean quit2 = false;

                        while (!quit2) {

                            System.out.println("1. Balance\n" +
                                    "2. Log out\n" +
                                    "0. Exit");
                            System.out.print(">");
                            chosenOption = scanner.nextInt();
                            scanner.nextLine();
                            int balance = 0;
                            System.out.println();
                            switch (chosenOption) {
                                case 1: {
                                    System.out.println();
                                    System.out.println("Balance : " + balance);
                                    break;
                                }
                                case 2: {
                                    System.out.println();
                                    System.out.println("You have successfully logged out! ");
                                    quit2 = true;
                                    break;
                                }
                                case 0: {
                                    quit = true;
                                    System.out.println("Bye! ");
                                    break;
                                }
                            }
                        }

                    } else {
                        System.out.println("Wrong card number or PIN! ");
                    }
                }
            }
        }
    }

}