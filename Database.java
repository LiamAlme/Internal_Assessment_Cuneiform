import java.awt.*;
import java.io.*;
import java.util.LinkedList;

public class Database implements Serializable{
    private static final long serialVersionUID = 1L;
    public LinkedList<Sign> database = new LinkedList<>();

    public void add(Sign new_sign){
        this.database.add(new_sign);
    }

    public boolean delete(String sign){
        int removal = search_sign(sign);
        System.out.println(removal);
        if(removal>=0){
            this.database.remove(removal);
            this.write_to_file();
            return true;
        }
        return false;
    }

    public void sort_sign(){
        //bubble sort
        for(int i = 0; i<this.database.size()-1; i++){
            for(int j = 0; j<this.database.size()-1; j++){
                if((this.database.get(j).getSign().compareTo(this.database.get(j+1).getSign()))>0){
                    Sign temp = this.database.get(j+1);
                    this.database.set(j+1, this.database.get(j));
                    this.database.set(j, temp);
                }
            }
        }

    }

    private int search_sign(String sign){
        //binary search
        //returns position in database
        sort_sign();
        int low = 0;
        int high = this.database.size();
        int mid = (low + high) / 2;
        while(low != high){
            if(sign.compareToIgnoreCase(this.database.get(mid).getSign()) == 0){
                return mid;
            }
            if(sign.compareToIgnoreCase(this.database.get(mid).getSign()) > 0){
                low = mid;
            }
            if(sign.compareToIgnoreCase(this.database.get(mid).getSign()) < 0){
                high = mid;
            }
            mid = (low + high) / 2;
        }
        return -1;
    }

    public LinkedList<Sign> search_phonetic(String phonetic_reading){
        //Returns a list of sign objects
        LinkedList<Sign> matching_signs = new LinkedList<Sign>();
        for (Sign sign : this.database) {
            if (sign.getPhonetic_readings().contains(phonetic_reading)) {
                matching_signs.add(sign);
            }
        }
        return matching_signs;
    }

    public LinkedList<Sign> search_logographic(String logographic_reading){
        //Returns a list of sign objects
        LinkedList<Sign> matching_signs = new LinkedList<>();
        for (Sign sign : this.database) {
            if (sign.getLogographic_readings().contains(logographic_reading)) {
                matching_signs.add(sign);
            }
        }
        return matching_signs;
    }

    public void write_to_file(){
        //Writes entire database class object to a file
        try {
            FileOutputStream fileOut = new FileOutputStream("Database");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this.database);
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
            LinkedList temp = (LinkedList) objectIn.readObject();
            this.database = temp;
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
