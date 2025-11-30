import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println(dateD(1));
        System.out.println(dateD(-1));
        System.out.println(dateD(10));
        System.out.println(dateD(-10));

    }

    public static String dateD(int n){
        SimpleDateFormat dateFormat  = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( calendar.getTime());
        calendar.add(Calendar.DATE, n );
        String date = dateFormat.format(calendar.getTime());

        return date;
    }
}

