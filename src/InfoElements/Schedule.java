package InfoElements;

public class Schedule {
    public static final int PARTITION = 30;
    static final int STARTWORKMINUTES = 720;
    static final int ENDWORKMINUTES = 1440;


    public static int[] refractorTime(String startTime, String minutes){
        String[] startTimeBuff = startTime.split(":");

        int pass = (Integer.parseInt(startTimeBuff[0])*60 + Integer.parseInt(startTimeBuff[1]) - STARTWORKMINUTES)/PARTITION;
        int count = Integer.parseInt(minutes)/PARTITION;

        return new int[]{pass, count};
    }

    public static int getPartsCount(){
        return (ENDWORKMINUTES - STARTWORKMINUTES) / PARTITION;
    }
}
