import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        database.initialize();
        System.out.println(database);

        JFrame main_menu = new JFrame();//creating instance of JFrame
        main_menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main_menu.setSize(400, 500);//400 width and 500 height
        main_menu.setLayout(null);//using no layout managers

        JButton add_to_database = new JButton("Add a Sign");
        add_to_database.setBounds(10, 10, 365, 75);

        add_to_database.addActionListener(e -> add_to_database(database));

        JButton view_all = new JButton("View All Signs");
        view_all.setBounds(10, 95, 365, 75);

        view_all.addActionListener(e -> view_all(database));

        main_menu.add(add_to_database);//adding button in JFrame
        main_menu.add(view_all);
        main_menu.setVisible(true);//making the frame visible
    }

    public static void add_to_database(Database database){
        JFrame add_menu = new JFrame();
        add_menu.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        add_menu.setSize(400,500);
        add_menu.setLayout(null);

        Label sign_entry_label = new Label("Sign:");
        sign_entry_label.setBounds(10,10,50,20);
        TextField sign_entry = new TextField();
        sign_entry.setBounds(150,10,150,20);

        Label phonetic_entry_label = new Label("Phonetic Reading:");
        phonetic_entry_label.setBounds(10,35,140,20);
        TextField phonetic_entry = new TextField();
        phonetic_entry.setBounds(150,35,150,20);

        Label logographic_entry_label = new Label("Logographic Reading:");
        logographic_entry_label.setBounds(10,60,140,20);
        TextField logographic_entry = new TextField();
        logographic_entry.setBounds(150,60,150,20);

        JButton add_phonetic = new JButton("Add");
        add_phonetic.setBounds(300,35,60,20);
        add_phonetic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                phonetic_entry.setText("");
            }
        });

        JButton add_logographic = new JButton("Add");
        add_logographic.setBounds(300,60,60,20);
        add_logographic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(logographic_entry.getText());
                logographic_entry.setText("");
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
        add_menu.setVisible(true);
    }

    public static void view_all(Database database){
        database.sort_sign();
        JFrame view_menu = new JFrame();
        view_menu.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        view_menu.setSize(400,500);
        view_menu.setLayout(null);

        List signs = new List(5);
        signs.setBounds(10,10,365,75);
        for(int i = 0; i < database.database.size(); i++){
            signs.add(database.database.get(i).toString());
        }
        view_menu.add(signs);

        view_menu.setVisible(true);
    }
}