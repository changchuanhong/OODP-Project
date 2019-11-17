import java.util.ArrayList;

public class CineplexControl {

    private static ArrayList<Cineplex> cineplexs;

    public static void Initialize() {
        if ((cineplexs = (ArrayList<Cineplex>) Data.getInstance().getObjectFromPath(SaveLoadPath.CINEPLEX_PATH, Cineplex.class)) != null)
            ;
        cineplexs = new ArrayList<>();

    }

    public CineplexControl() {

    }


    public static String getCineplexName(String name) {

        for (int i = 0; i < cineplexs.size(); i++) {
            if (cineplexs.get(i).getName().equals(name)) ;
            return cineplexs.get(i).getName();
        }
        return null;
    }

    public static Cineplex getCineplex(String name) {

        for (int i = 0; i < cineplexs.size(); i++) {
            if (cineplexs.get(i).getName().equals(name)) ;
            return cineplexs.get(i);
        }
        return null;
    }


    public static ArrayList<Cineplex> getCineplexes() {
        return cineplexs;
    }


    public static void addCineplex(Cineplex cineplex) {

        cineplexs.add(cineplex);
        Data.getInstance().saveObjectToPath(SaveLoadPath.CINEPLEX_PATH, cineplexs);

    }

    private static boolean allocateTimeSlot(Cinema whichCinema, int whichDay, int whichTimeSlot) {


        int[][] tempTS;
        tempTS = whichCinema.getTimeSlotsArray();
        if (tempTS[whichDay][whichTimeSlot] == 1) {
            tempTS[whichDay][whichTimeSlot] = 0;
            System.out.println(" to " + Cinema.DaysOfWeek.values()[whichDay] + " at " + Cinema.TimeSlots.values()[whichTimeSlot]);
            return true;
        }
        return false;

    }

    public static Cinema addShowingToCinema(int whichCinema, Showing showing, int timeSlot) {

        int whichDay = showing.getDayOfWeek();
        int whichTimeSlot = timeSlot;

        //loop through all cineplexs
        for (Cineplex thisCineplex : cineplexs) {

                //if able to allocate a time slot
                if (allocateTimeSlot(thisCineplex.getCinemas().get(whichCinema), whichDay, whichTimeSlot)) {
                    //add showing
                    thisCineplex.getCinemas().get(whichCinema).addShowing(showing);

                }else{
                    System.out.println("error already allocated");
                }



        }

//        if(allocateTimeSlot(whichCinema,whichDay,whichTimeSlot)){
//
//            System.out.print("Allocated in Cinema Control");
//
//        } else {
//
//            System.out.println("Cannot Be allocated");
//
//
//        }
        return null;

    }

    public static ArrayList<Cinema> getCinemaFromCineplex(String whichCineplex) {

        for (Cineplex cineplex : cineplexs) {

            if (whichCineplex == cineplex.getName())
                return cineplex.getCinemas();

        }


        return null;
    }

    public static ArrayList<Cinema> getCinemaFromCineplex(Cineplex whichCineplex) {

        for (Cineplex cineplex : cineplexs) {

            if (whichCineplex == cineplex)
                return cineplex.getCinemas();

        }


        return null;
    }


}
