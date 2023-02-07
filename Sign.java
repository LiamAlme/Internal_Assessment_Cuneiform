import java.util.LinkedList;
import java.io.Serializable;

public class Sign implements Serializable{
    private static final long serialVersionUID = 1L;
    private final String sign;
    private LinkedList<String> phonetic_readings = new LinkedList<String>();
    private LinkedList<String> logographic_readings = new LinkedList<String>();

    public Sign(String sign) {
        this.sign = sign;
    }

    public void add_phonetic_reading(String new_phonetic){
        this.phonetic_readings.add(new_phonetic);
    }

    public void add_logographic_reading(String new_logographic){
        this.logographic_readings.add(new_logographic);
    }

    public String getSign() {
        return sign;
    }

    public LinkedList<String> getPhonetic_readings() {
        return phonetic_readings;
    }

    public void setPhonetic_readings(LinkedList<String> phonetic_readings) {
        this.phonetic_readings = phonetic_readings;
    }

    public LinkedList<String> getLogographic_readings() {
        return logographic_readings;
    }

    public void setLogographic_readings(LinkedList<String> logographic_readings) {
        this.logographic_readings = logographic_readings;
    }

    @Override
    public String toString() {
        return "Sign{" +
                "sign='" + sign + '\'' +
                ", phonetic_readings=" + phonetic_readings +
                ", logographic_readings=" + logographic_readings +
                '}';
    }
}
