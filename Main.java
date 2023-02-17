import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        database.initialize();
        System.out.println(database);

        JFrame main_menu = new JFrame();
        main_menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main_menu.setSize(400, 475);
        main_menu.setLayout(null);

        JButton add_to_database = new JButton("Add a Sign");
        add_to_database.setBounds(10, 10, 365, 75);
        add_to_database.addActionListener(e -> add_to_database(database));

        JButton view_all = new JButton("View All Signs");
        view_all.setBounds(10, 95, 365, 75);
        view_all.addActionListener(e -> view_all(database));

        JButton delete_sign = new JButton("Delete Sign");
        delete_sign.setBounds(10, 180, 365, 75);
        delete_sign.addActionListener(e -> delete_sign(database));

        JButton search_phonetic = new JButton("Search by Phonetic");
        search_phonetic.setBounds(10, 265, 175, 75);
        search_phonetic.addActionListener(e -> search_phonetic(database));

        JButton search_logographic = new JButton("Search by Logographic");
        search_logographic.setBounds(200, 265, 175, 75);
        search_logographic.addActionListener(e -> search_logographic(database));

        JButton flashcards = new JButton("Flashcards");
        flashcards.setBounds(10, 350, 365, 75);
        flashcards.addActionListener(e -> flashcards(database));

        main_menu.add(add_to_database);
        main_menu.add(view_all);
        main_menu.add(delete_sign);
        main_menu.add(search_phonetic);
        main_menu.add(search_logographic);
        main_menu.add(flashcards);
        main_menu.setVisible(true);
    }

    public static void add_to_database(Database database){
        JFrame add_menu = new JFrame();
        add_menu.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        add_menu.setSize(400,500);
        add_menu.setLayout(null);

        Font font = new Font("Segoe UI Historic", Font.PLAIN,12);
        System.out.println(font.canDisplayUpTo("𒀡"));
        System.out.println();

        Label sign_entry_label = new Label("Sign:");
        sign_entry_label.setFont(font);
        System.out.println(sign_entry_label.getFont());
        sign_entry_label.setBounds(10,10,50,20);
        TextField sign_entry = new TextField();
        sign_entry.setFont(font);
        sign_entry.setBounds(150,10,150,20);

        Label phonetic_entry_label = new Label("Phonetic Reading:");
        phonetic_entry_label.setBounds(10,35,140,20);
        TextField phonetic_entry = new TextField();
        phonetic_entry.setBounds(150,35,150,20);

        Label logographic_entry_label = new Label("Logographic Reading:");
        logographic_entry_label.setBounds(10,60,140,20);
        TextField logographic_entry = new TextField();
        logographic_entry.setBounds(150,60,150,20);

        List phonetic_readings = new List();
        phonetic_readings.setFont(font);
        phonetic_readings.setBounds(10,120,150,100);
        List logographic_readings = new List();
        logographic_readings.setBounds(200,120,150,100);

        JButton add_phonetic = new JButton("Add");
        add_phonetic.setBounds(300,35,60,20);
        add_phonetic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Objects.equals(phonetic_entry.getText(), "")){
                    phonetic_readings.add(phonetic_entry.getText());
                    phonetic_entry.setText("");
                }
            }
        });

        JButton add_logographic = new JButton("Add");
        add_logographic.setBounds(300,60,60,20);
        add_logographic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Objects.equals(logographic_entry.getText(), "")){
                    logographic_readings.add(logographic_entry.getText());
                    logographic_entry.setText("");
                }
            }
        });

        Label phonetic_readings_label = new Label("Phonetic Readings");
        phonetic_readings_label.setBounds(10, 100, 120, 20);

        Label logographic_readings_label = new Label("Logographic Readings");
        logographic_readings_label.setBounds(200, 100, 150, 20);

        JButton save = new JButton("Save");
        save.setBounds(10,250,150,80);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Objects.equals(sign_entry.getText(), "")){
                    Sign new_sign = new Sign(sign_entry.getText());
                    if(phonetic_readings.getItemCount()>0){
                        for(int i = 0; i< phonetic_readings.getItemCount(); i++){
                            new_sign.add_phonetic_reading(phonetic_readings.getItem(i));
                        }
                    }
                    if(logographic_readings.getItemCount()>0){
                        for(int i = 0; i< logographic_readings.getItemCount(); i++){
                            new_sign.add_logographic_reading(logographic_readings.getItem(i));
                        }
                    }
                    database.add(new_sign);
                    database.write_to_file();
                    add_menu.dispose();
                }

            }
        });

        add_menu.add(sign_entry_label);
        add_menu.add(sign_entry);
        add_menu.add(phonetic_entry_label);
        add_menu.add(phonetic_entry);
        add_menu.add(logographic_entry_label);
        add_menu.add(logographic_entry);
        add_menu.add(add_phonetic);
        add_menu.add(add_logographic);
        add_menu.add(phonetic_readings_label);
        add_menu.add(logographic_readings_label);
        add_menu.add(phonetic_readings);
        add_menu.add(logographic_readings);
        add_menu.add(save);
        add_menu.setVisible(true);
    }

    public static void view_all(Database database){
        database.sort_sign();
        JFrame view_menu = new JFrame();
        view_menu.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        view_menu.setSize(400,500);
        view_menu.setLayout(null);

        List signs = new List(5);
        signs.setBounds(10,10,365,450);
        for(int i = 0; i < database.database.size(); i++){
            signs.add(database.database.get(i).toString());
        }
        view_menu.add(signs);

        view_menu.setVisible(true);
    }

    private static void delete_sign(Database database) {
        JFrame delete_menu = new JFrame();
        delete_menu.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        delete_menu.setSize(400,500);
        delete_menu.setLayout(null);

        JFrame not_found = new JFrame();
        not_found.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        not_found.setSize(400,200);
        Label not_found_label = new Label("Sign not found in database");
        not_found.add(not_found_label);

        Label sign = new Label("Sign:");
        sign.setBounds(10,10,50,20);
        TextField delete_entry = new TextField();
        delete_entry.setBounds(60,10,150,20);

        JButton delete = new JButton("Delete");
        delete.setBounds(60,40,100,50);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Objects.equals(delete_entry.getText(), "")){
                    if(database.delete(delete_entry.getText())){
                        database.write_to_file();
                        delete_menu.dispose();
                    }
                    else{
                        not_found.setVisible(true);
                    }
                }
            }
        });

        delete_menu.add(sign);
        delete_menu.add(delete_entry);
        delete_menu.add(delete);
        delete_menu.setVisible(true);
    }

    private static void search_phonetic(Database database) {
        JFrame search_menu = new JFrame();
        search_menu.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        search_menu.setSize(400,500);
        search_menu.setLayout(null);

        Label phonetic = new Label("Phonetic Meaning:");
        phonetic.setBounds(10,10,105,20);
        TextField phonetic_entry = new TextField();
        phonetic_entry.setBounds(120,10,150,20);

        List results = new List();
        results.setBounds(10,100,365,120);

        JButton search = new JButton("Search");
        search.setBounds(60,40,100,50);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Objects.equals(phonetic_entry.getText(), "")){
                    LinkedList<Sign> phonetic_results = database.search_phonetic(phonetic_entry.getText());
                    results.removeAll();
                    for (Sign phonetic_result : phonetic_results) {
                        results.add(String.valueOf(phonetic_result));
                    }
                    phonetic_entry.setText("");
                }
            }
        });
        search_menu.add(phonetic);
        search_menu.add(phonetic_entry);
        search_menu.add(search);
        search_menu.add(results);
        search_menu.setVisible(true);
    }

    private static void search_logographic(Database database) {
        JFrame search_menu = new JFrame();
        search_menu.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        search_menu.setSize(400,500);
        search_menu.setLayout(null);

        Label logographic = new Label("Logographic Meaning:");
        logographic.setBounds(10,10,105,20);
        TextField logographic_entry = new TextField();
        logographic_entry.setBounds(120,10,150,20);

        List results = new List();
        results.setBounds(10,100,365,120);

        JButton search = new JButton("Search");
        search.setBounds(60,40,100,50);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Objects.equals(logographic_entry.getText(), "")){
                    LinkedList<Sign> phonetic_results = database.search_logographic(logographic_entry.getText());
                    results.removeAll();
                    for (Sign phonetic_result : phonetic_results) {
                        results.add(String.valueOf(phonetic_result));
                    }
                    logographic_entry.setText("");
                }
            }
        });
        search_menu.add(logographic);
        search_menu.add(logographic_entry);
        search_menu.add(search);
        search_menu.add(results);
        search_menu.setVisible(true);
    }

    private static void flashcards(Database database){

    }
}