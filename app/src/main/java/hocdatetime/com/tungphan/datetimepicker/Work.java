package hocdatetime.com.tungphan.datetimepicker;

/**
 * Created by PC on 26-3-2018.
 */

public class Work {
    private String name;
    private String des;
    private String date;
    private String time;

    public Work(String name, String des, String date, String time) {
        this.name = name;
        this.des = des;
        this.date = date;
        this.time = time;
    }

    public String toString() {
        return name+ " - "+ date+" - "+time;
    }

    public String getDes() {
        return des;
    }
}
