//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    static final Scanner scanner = new Scanner(System.in);
    static List<String> userList = new ArrayList<>();
    static String userChoice;
    static boolean quit = false;

    public static void main(String[] args) {
        do {
            printList();
            printMenu();
            getUserInput();
            processChoice();
        } while (!quit);

        System.out.println("Goodbye!");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("Please choose from the following options:");
        System.out.println("A/a: Add an item to the list");
        System.out.println("D/d: Delete an item from the list");
        System.out.println("I/i: Insert an item into the list");
        System.out.println("P/p: Print all items in the list");
        System.out.println("Q/q: Quit program");
    }

    private static void getUserInput() {
        userChoice = SafeInput.getRegExString(scanner, "\nPlease select a menu option (A/D/I/P/Q): ", "(?i)^[adipq]$").toUpperCase();
    }

    private static void printList() {
        if (userList.isEmpty()) {
            System.out.println("\nThe list is empty.\n");
        } else {
            System.out.println("\nThe list contains the following items:\n");
            for (int i = 1; i <= userList.size(); i++) {
                System.out.println("Item " + i + ": " + userList.get(i-1));
            }
            System.out.println("\n");
        }
    }

    private static void processChoice() {
        switch (userChoice) {
            case "A":
                addItem();
                break;
            case "D":
                if (userList.isEmpty()) {
                    System.out.println("\nYou can't remove an item from an empty list!\n");
                } else {
                    deleteItem();
                }
                break;
            case "I":
                if (userList.isEmpty()) {
                    System.out.println("Let's add our first item.");
                    addItem();
                } else {
                    insertItem();
                }
                break;
            case "P":
                printList();
                break;
            case "Q":
                quit = SafeInput.getYNConfirm(scanner, "Are you sure you want to quit the list app?");
                break;
       }
    }

    private static void addItem() {
        String newItem = SafeInput.getNonZeroLenString(scanner, "Please enter a new item");
        userList.add(newItem);
    }

    private static void deleteItem() {
        int index = SafeInput.getRangedInt(scanner, "Please enter the item number to delete", 1, userList.size());
        userList.remove(index-1);
    }

    private static void insertItem() {
        int index = SafeInput.getRangedInt(scanner, "Please enter the index of the item to insert BEFORE", 1, userList.size());
        String newItem = SafeInput.getNonZeroLenString(scanner, "Please enter a new item");
        userList.add(index-1, newItem);
    }
}
