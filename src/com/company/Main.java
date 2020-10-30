package com.company;

import FootballClub.FootballClub;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.security.KeyStore;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        LinkedList<FootballClub> footballClubsLinkedList = new LinkedList<FootballClub>();
        LinkedList<FootballClub> footballClubsLinkedList1 = new LinkedList<FootballClub>();
        LinkedList<FootballClub> footballClubsLinkedList2 = new LinkedList<FootballClub>();
        LinkedList<FootballClub> commonInformationAboutFC = new LinkedList<FootballClub>();
        HashMap<String, ArrayList<String>> cityAndNamesMap = new HashMap<>();

        Scanner scannerForChoice = new Scanner(System.in);

        System.out.println("1 - Створити карту з ключем місто продюсера та значенням –списку всіх футбольних клубів" +
                " цього міста. \n2 - Видрукувати з кожного списку тільки n перших позицій цього списку. \n3 - " +
                "Визначити скільки міст мають футбольні клуби з однією назвою. \n4 - Створити" +
                "  колекцію, що складається з спільних членів двох переліків. \n\n 0 - Вихід.");

        int choice = scannerForChoice.nextInt();

        while(choice != 0) {
            if (choice == 1) {
                footballClubsLinkedList.clear();
                cityAndNamesMap.clear();
                footballClubsLinkedList = ReadFromFile("data.txt");
                if (footballClubsLinkedList != null) {
                    for (FootballClub fcVar : footballClubsLinkedList) {
                        System.out.println(fcVar);
                    }
                }

                // створення карти
                for (FootballClub fcVar : footballClubsLinkedList) {
                    if (cityAndNamesMap.containsKey(fcVar.getCity())) {
                        ArrayList<String> listOfNames = new ArrayList<String>(cityAndNamesMap.get(fcVar.getCity()));
                        listOfNames.add(fcVar.getName());
                        cityAndNamesMap.replace(fcVar.getCity(), listOfNames);
                    } else {
                        ArrayList<String> listOfNames = new ArrayList<>();
                        listOfNames.add(fcVar.getName());
                        cityAndNamesMap.put(fcVar.getCity(), listOfNames);
                    }
                }

                System.out.println();
                for (Map.Entry entry : cityAndNamesMap.entrySet()) {
                    System.out.println("Місто = " + entry.getKey() + ", Назви = " + entry.getValue());
                }

                System.out.println("Карту створено.");

            } else if (choice == 2) {

                // вивід тільки n перших елементів
                if (footballClubsLinkedList.size() > 0) {
                    System.out.println();
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Введіть кількість перших елементів - ");
                    int n = scanner.nextInt();
                    for (String entry : cityAndNamesMap.keySet()) {

                        try {
                            System.out.println("Місто - " + entry + "; Назва(и) - " + cityAndNamesMap.get(entry).subList(0, n));
                        } catch (Exception e) {
                            continue;
                        }
                    }
                    System.out.println("Карту виведено.");
                } else System.out.println("Зчитайте з файлу для початку.");
            }
            else if (choice == 3) {
                if(footballClubsLinkedList.size() > 0) {

                    //міст, які мають футбольні клуби з однією назвою
                    System.out.println();
                    HashMap<String, ArrayList<String>> nameAndCities = new HashMap<>();
                    for (FootballClub fcVar : footballClubsLinkedList) {
                        if (nameAndCities.containsKey(fcVar.getName())) {
                            ArrayList<String> listOfCities = new ArrayList<String>(nameAndCities.get(fcVar.getName()));
                            listOfCities.add(fcVar.getCity());
                            nameAndCities.replace(fcVar.getName(), listOfCities);
                        } else {
                            ArrayList<String> listOfCities = new ArrayList<>();
                            listOfCities.add(fcVar.getCity());
                            nameAndCities.put(fcVar.getName(), listOfCities);
                        }
                    }
                    System.out.println();
                    for (Map.Entry entry : nameAndCities.entrySet()) {
                        System.out.println("Назва = " + entry.getKey() + ", Міста = " + entry.getValue());
                    }

                    System.out.println("Міста, які мають футбольні клуби з однією назвою");
                }
                else System.out.println("Зчитайте з файлу для початку.");
            }
            else if (choice == 4) {
                footballClubsLinkedList1.clear();
                footballClubsLinkedList2.clear();
                commonInformationAboutFC.clear();
                footballClubsLinkedList1 = ReadFromFile("data1.txt");
                footballClubsLinkedList2 = ReadFromFile("data2.txt");
                // пошук спільнох інфи з двої файлів
                System.out.println("Спільна інформація");
                for (FootballClub fc1: footballClubsLinkedList1) {
                    for (FootballClub fc2: footballClubsLinkedList2) {
                        if(fc1.equalsFC(fc2)){
                            commonInformationAboutFC.add(fc1);
                        }
                    }
                }

                for (FootballClub fc: commonInformationAboutFC) {
                    System.out.println(fc);
                }

                System.out.println("\nВивід спільної інфи з двої файлів");
            }
            else  System.out.println("Незнайдено команди. Виберіть іншу.");

            System.out.println("\n\t\t\t\t\tМеню\n1 - Створити карту з ключем місто продюсера та значенням –списку всіх футбольних клубів" +
                    " цього міста. \n2 - Видрукувати з кожного списку тільки n перших позицій цього списку. \n3 - " +
                    "Визначити скільки міст мають футбольні клуби з однією назвою. \n4 - Створити" +
                    "  колекцію, що складається з спільних членів двох переліків. \n\n 0 - Вихід.");
            choice = scannerForChoice.nextInt();
        }
    }

    static LinkedList<FootballClub> ReadFromFile(String path){
        LinkedList<FootballClub> footballClubsLinkedList = new LinkedList<FootballClub>();
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                String[] words = str.split(" ");
                footballClubsLinkedList.add(new FootballClub(words[0], Integer.parseInt(words[1]), words[2]));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
        return footballClubsLinkedList;
    }
}
