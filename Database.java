import java.io.*;
import java.util.LinkedList;

public class Database implements Serializable{
    private static final long serialVersionUID = 1L;
    public LinkedList<Sign> database = new LinkedList<>();

    public void add(Sign new_sign){
        database.add(new_sign);
    }

    public void delete(String remove_sign){
        int removal = search_sign(remove_sign);
        if(removal>0){
            database.remove(removal);
        }
    }

    public void sort_sign(){
        //bubble sort
        for(int i = 0; i<database.size()-1; i++){
            for(int j = 0; j<database.size()-1; j++){
                if((database.get(j).getSign().compareTo(database.get(j+1).getSign()))>0){
                    Sign temp = database.get(j+1);
                    database.set(j+1, database.get(j));
                    database.set(j, temp);
                }
            }
        }

    }

    private int search_sign(String sign){
        //binary search
        //returns position in database
        sort_sign();
        int low = 0;
        int high = database.size() - 1;
        int mid = (low + high) / 2;
        while(low != high){
            if(sign.compareToIgnoreCase(database.get(mid).getSign()) == 0){
                return mid;
            }
            if(sign.compareToIgnoreCase(database.get(mid).getSign()) > 0){
                low = mid + 1;
            }
            if(sign.compareToIgnoreCase(database.get(mid).getSign()) < 0){
                high = mid - 1;
            }
            mid = (low + high) / 2;
        }
        return -1;
    }

    public LinkedList<Sign> search_phonetic(String phonetic_reading){
        //Returns a list of sign objects
        LinkedList<Sign> matching_signs = new LinkedList<>();
        for(int i = 0; i<database.size(); i++){
            if(database.get(i).getPhonetic_readings().contains(phonetic_reading)){
                matching_signs.add(database.get(i));
            }
        }
        return matching_signs;
    }

    public LinkedList<Sign> search_logographic(String logographic_reading){
        //Returns a list of sign objects
        LinkedList<Sign> matching_signs = new LinkedList<>();
        for(int i = 0; i<database.size(); i++){
            if(database.get(i).getLogographic_readings().contains(logographic_reading)){
                matching_signs.add(database.get(i));
            }
        }
        return matching_signs;
    }

    public void write_to_file(Database database){
        //Writes entire database class object to a file
        try {
            FileOutputStream fileOut = new FileOutputStream("Database");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(database);
            objectOut.close();
            System.out.println("Working");

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void initialize(){
        //initializes database from file
        try{
            FileInputStream fileIn = new FileInputStream("Database");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Database temp = (Database) objectIn.readObject();
            database = temp.database;
            objectIn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Database{" +
                "database=" + database +
                '}';
    }
}
