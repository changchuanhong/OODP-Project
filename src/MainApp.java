import java.util.ArrayList;
import java.util.Scanner;
/**
 * main screen
 */

public class MainApp {

	static Scanner sc = new Scanner(System.in);
/**
 * main function, initialises control classes
 */
	public static void main(String[] args) throws InterruptedException {

		ShowingControl.Reinitialize();
		CineplexControl.Reinitialize();
		MovieControl.Reinitialize();
		ReviewControl.Reinitialize();

		System.out.println("Welcome to MOBLIMA, the best way book your movie tickets.");
		System.out.println("What would you like to do?");
		menu();
	}
	/**
	 * Prints menu to login or continue as guest
	 */
	private static void menu() throws InterruptedException {


		int sc_in;

		do {
			System.out.println("1. Login/Register \n2. Continue as guest \n3. Exit");
			sc_in = sc.nextInt();
			switch (sc_in) {
				case 1:
                    User lis = null;
                    while(lis == null) {
                        lis = new LoginApp().run();
                    }


					if(lis.getType().equals("client")){

						new ClientApp((Client) lis);

					}else if(lis.getType().equals("staff")){

						new StaffApp((Staff) lis);

					}else {
                        System.out.println("error in determining class");
					}

					break;

				case 2:

					new ClientApp(null);
					break;

				case 3:

					System.exit(1);
					break;

				case 22:

					instantiateTestData();

					break;



				default:
					System.out.println("Invalid input, please choose from the following:");
					break;

			}

		} while (sc_in != 3);
	}

/**
 *
 * Creating initial test data
 *
 */
	private static void instantiateTestData() throws InterruptedException {

		ArrayList<String> holidays = new ArrayList<>();
		holidays.add("20200101");
		Prices price = new Prices(10, 7, 2, 3, 1, 2, holidays);
		ArrayList<Prices> prices = new ArrayList<>();
		prices.add(price);


		ArrayList<User> user = new ArrayList<>();
		Client client = new Client("hello","korea","012021314","a@gmail.com","Beck Gillespe");
		user.add(client);

		User staffmember = new Staff("admin","admin","raymond");
		user.add(staffmember);

		ArrayList<Cineplex> cpes = new ArrayList<>();
		cpes.add(new Cineplex("Jurong"));
		cpes.add(new Cineplex("Orchard"));
		cpes.add(new Cineplex("Central"));


		String[] castArray = new String[2];
		ArrayList<Movie> movieListings = new ArrayList<>();
		castArray[0] = "Zhou Dongyu";
		castArray[1] = "Jackson Yee";
		movieListings.add(new Movie(
				"Better Days",
				Movie.Status.Showing,
				"Seventeen-year-old Nian is the subject of cruel bullying at high school when she meets Bei, a tough street kid. The two teenagers find a kindred spirit in each other that gradually rises above love, forming a world of their own. But the cocoon is crushed when they are being dragged into a teenage girl murder case as prime suspects. An emotional roller coaster that is heartwarming and heartbreaking at the same time, the China coming-of-age movie offers thought-provoking insights into the intense competition faced by nearly 10 million teenagers every year who sit for the National College Entrance Examination and national issues of school bullying. If you are one amongst 10 million to secure a promising future with a topnotch college passport, would you kill to do it?",
				"Derek Tsang",
				"NR",
				castArray));


		castArray[0] ="Chloe Benne";
		castArray[1] ="TenzNorgay Trainor";
		movieListings.add(new Movie("Abominable",
				Movie.Status.Showing,"Abominable takes audiences on an epic 2,000-mile adventure from the streets of a Chinese city to the breathtaking Himalayan snowscapes. When teenage Yi (Chloe Bennet) encounters a young Yeti on the roof of her apartment building, she and her friends, Jin (Tenzing Norgay Trainor) and Peng (Albert Tsai), name him 'Everest' and embark on an epic quest to reunite the magical creature with his family at the highest point on Earth.",
				"Jill Culton",
				"PG",
				castArray));



		castArray[0] ="Woody Harrelson";
		castArray[1] ="Jesse Eisenberg";
		movieListings.add(new Movie("Zombieland: Double Tap",
				Movie.Status.Showing,
				"A decade after Zombieland became a hit film and a cult classic, the lead cast (Woody Harrelson, Jesse Eisenberg, Abigail Breslin, and Emma Stone) have reunited with director Ruben Fleischer (Venom) and writers Rhett Reese & Paul Wernick (Deadpool) for Zombieland 2. In the sequel, through comic mayhem that stretches from the White House and through the heartland, these four slayers must face off against the many new kinds of zombies that have evolved since the first movie, as well as some new human survivors. But most of all, they have to face the growing pains of their own snarky, makeshift family.",
				"Ruben Fleischer",
				"R",
				castArray));



		castArray[0] ="Joaquin Phoenix";
		castArray[1] ="Robert De Niro";
		movieListings.add(new Movie("Joker",
				Movie.Status.Showing,
				"A failed stand-up comedian is driven insane and becomes a psychopathic murderer.",
				"Todd Phillips",
				"R",
				castArray));

		castArray[0] = "Matt Damon";
		castArray[1] = "Christian Bale";
		movieListings.add(new Movie(
				"Ford vs Ferrari",
				Movie.Status.Showing,
				"Academy Award-winners Matt Damon and Christian Bale star in FORD v FERRARI, based on the remarkable true story of the visionary American car designer Carroll Shelby (Damon) and the fearless British-born driver Ken Miles (Bale), who together battled corporate interference, the laws of physics, and their own personal demons to build a revolutionary race car for Ford Motor Company and take on the dominating race cars of Enzo Ferrari at the 24 Hours of Le Mans in France in 1966.",
				"James Mangold",
				"PG",
				castArray));
		
		int cineplex =0;
		int cinema =0;
		int day =20;
		int dotw =1;
		int tmslot =1;

        ArrayList<Showing> newShowings = new ArrayList<>();
		for (day = 20; day < 25; day++){
			for (cineplex =0;cineplex < 2;cineplex++){
				for (cinema = 0; cinema < 3;cinema++){
					for (tmslot =0; tmslot <5;tmslot++){
						newShowings.add(new Showing(
								cpes.get(cineplex).getCinemas().get(cinema),
								cpes.get(cineplex),
								movieListings.get(tmslot),
								"201911"+day+dotw+tmslot,
								"normal"
						));
					}
				}
			}
			dotw=dotw%6;
			dotw++;
		}



		Data.saveObjectToPath(SaveLoadPath.CINEPLEX_PATH,cpes);
		ShowingControl.addShowing(newShowings);
		Data.saveObjectToPath(SaveLoadPath.USER_PATH,user);
		Data.saveObjectToPath(SaveLoadPath.PRICE_PATH, prices);
		Data.saveObjectToPath(SaveLoadPath.MOVIE_PATH,movieListings);

		BookingControl bookingControl = new BookingControl(new Client("testuser", "testpassword", "w", "w", "w"),newShowings.get(0));

		bookingControl.addTicket("adult",1,8);
		bookingControl.addTicket("adult",2,1);
		bookingControl.addTicket("adult",8,1);
		bookingControl.completeBooking();

		bookingControl.completeBooking();

		BookingControl bookingControl2 = new BookingControl(new Client("testuser", "testpassword", "w", "w", "w"),newShowings.get(2));

		bookingControl2.addTicket("adult",1,8);
		bookingControl2.addTicket("adult",2,1);
		bookingControl.completeBooking();

		bookingControl2.completeBooking();

		BookingControl bookingControl3 = new BookingControl(new Client("testuser", "testpassword", "w", "w", "w"),newShowings.get(3));

		bookingControl3.addTicket("adult",1,8);
		bookingControl3.addTicket("adult",2,1);
		bookingControl3.addTicket("adult",8,1);
		bookingControl.completeBooking();

		bookingControl3.completeBooking();

		BookingControl bookingControl4 = new BookingControl(client,newShowings.get(4));

		bookingControl4.addTicket("adult",1,8);
		bookingControl4.addTicket("adult",2,1);
		bookingControl4.addTicket("adult",8,1);
		bookingControl4.addTicket("adult",2,5);
		bookingControl.completeBooking();

		bookingControl4.completeBooking();
		BookingControl bookingControl5 = new BookingControl(client,newShowings.get(5));

		bookingControl5.addTicket("adult",1,8);
		bookingControl5.addTicket("adult",2,1);
		bookingControl5.addTicket("adult",8,1);
		bookingControl5.addTicket("adult",8,1);
		bookingControl5.addTicket("adult",7,1);
		bookingControl5.addTicket("adult",6,1);
		bookingControl5.completeBooking();

        ArrayList<Review> reviews = new ArrayList<>();
        reviews.add(new Review("Really capturing!",
				movieListings.get(0).getName(),
				5,
				client));
        reviews.add(new Review("Horrible acting, wouldn't recommend.",
				movieListings.get(1).getName(),
				1,
				client));
		reviews.add(new Review("its ok i like car",
				movieListings.get(1).getName(),
				4,
				client));
        reviews.add(new Review("Really scary and horryfying. Zombies looked real.",
				movieListings.get(2).getName(), 5, client));

        reviews.add(new Review("Todd acted great. Storyline could have been better though.",
				movieListings.get(3).getName(), 4, client));

        reviews.add(new Review("Movie was overall okay. Some parts were worth watching.",
				movieListings.get(4).getName(), 3, client));

		reviews.add(new Review("good movie!",
				movieListings.get(4).getName(), 1, client));

		reviews.add(new Review("worth watching.",
				movieListings.get(4).getName(), 2, client));

        ReviewControl.addReview(reviews);

		System.out.println("There are " + CineplexControl.getCineplexes().size() + " Cineplexes each with " + CineplexControl.getCineplexes().get(0).getCinemas().size() + " Cinemas");
        System.out.println("There are " + ReviewControl.getAllReviews().size() + " Reviews");
		System.out.println("There are " + ShowingControl.getAllShowings().size() + " Showings");
        System.out.println("There are " + MovieControl.getAllMovies().size() + " Movies");

    }


}
