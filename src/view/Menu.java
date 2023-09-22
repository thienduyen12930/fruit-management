package view;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menu<T> {

    protected String title;
    protected ArrayList mChoice;

    public Menu(String td, String[] mc) {
        title = td;
        mChoice = new ArrayList<>();
        for (String s : mc) {
            mChoice.add((T) s);
        }
    }

    public void disPlay() {
        System.out.println(title);
        System.out.println("===========================");
        for (int i = 0; i < mChoice.size(); i++) {
            System.out.println("|"+(i + 1) + "." + mChoice.get(i)+"|");

        }
        System.out.println("===========================");

    }

    public int getSelected() {
        disPlay();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your choice :");
        return scanner.nextInt();

    }

    public abstract void execute(int n);

    public void runMenu() {
        while (true) {
            int n = getSelected();
            execute(n);
            if (n > mChoice.size()) {
                break;
            }

        }

    }

}
