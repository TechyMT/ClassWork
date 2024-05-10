import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class cha {

    private static final Map<String, String[]> movies = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    static {
        movies.put("Interstellar", new String[] { "Morning: 10:00 AM", "Afternoon: 2:00 PM", "Evening: 6:00 PM" });
        movies.put("The Dark Knight", new String[] { "Morning: 11:00 AM", "Afternoon: 3:00 PM", "Evening: 7:00 PM" });
        movies.put("Dune", new String[] { "Morning: 9:30 AM", "Afternoon: 1:30 PM", "Evening: 5:30 PM" });
    }

    public static void main(String[] args) {
        System.out.println("\nHello! Welcome to TeaBot. How can I assist you today?");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine().trim();
            String response = respond(userInput);
            System.out.println("TeaBot: " + response);

            // extract information for booking tickets
            if (userInput.toLowerCase().contains("book tickets")) {
                bookTickets();
            }

            // check if the user wants to end the conversation
            if (userInput.toLowerCase().contains("bye") || userInput.toLowerCase().contains("goodbye")) {
                break;
            }
        }
    }

    private static String respond(String userInput) {
        Pattern pattern;
        Matcher matcher;

        String[][] patternsAndResponses = {
                { "hi|hello|hey", "Hello! How can I assist you today?", "Hey there! What can I do for you?",
                        "Hi! Ready to book some movie tickets?" },
                { "how are you", "I am just a bot, but I am always ready to help you book tickets!",
                        "I am doing well, thanks for asking. How can I assist you today?" },
                { "(.*) movie",
                        "Here is the list of movies: " + String.join(", ", movies.keySet())
                                + ". Which movie would you like to watch?",
                        "Sure! Here are the movies currently playing: " + String.join(", ", movies.keySet()) + "." },
                { "(.*) ticket", "How many tickets do you need?", "Sure! How many tickets would you like to book?",
                        "Got it! How many tickets do you want to book?" },
                { "(.*) showtime", "Here are the showtimes for the available movies:" + getShowtimes() },
                { "(.*) timing", "Here are the timings for each movie:" + getShowtimes() },
                { "(.*) (morning|afternoon|evening|night)",
                        "We have showtimes available in the morning, afternoon, evening, and night. When would you like to watch?" },
                { "(.*) book tickets", "Sure! Let's proceed with booking tickets." },
                { "(.*) (bye|goodbye)", "Thank you for visiting TeaBot. Have a great day!",
                        "Goodbye! Enjoy your movie!", "Bye! See you soon." }
        };

        for (String[] patternAndResponses : patternsAndResponses) {
            pattern = Pattern.compile(patternAndResponses[0]);
            matcher = pattern.matcher(userInput.toLowerCase());
            if (matcher.find()) {
                return patternAndResponses[(int) (Math.random() * (patternAndResponses.length - 1)) + 1];
            }
        }
        return "I'm sorry, I didn't understand that.";
    }

    private static void bookTickets() {
        System.out.print("Which movie would you like to watch? : ");
        String movie = scanner.nextLine().trim();

        System.out.print("How many tickets do you need? : ");
        int tickets = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("At what time would you like to watch the movie? : ");
        String showtime = scanner.nextLine().trim();

        String bookingResponse = bookTickets(movie, tickets, showtime);
        System.out.println("TeaBot: " + bookingResponse);
    }

    private static String bookTickets(String movie, int tickets, String showtime) {
        // calculate total bill
        int ticketPrice = 100; // assuming a fixed price for now
        int totalBill = tickets * ticketPrice;
        return String.format("Booking confirmed! You have booked %d tickets for %s at %s.\nTotal amount: Rs. %d.",
                tickets, movie, showtime, totalBill);
    }

    private static String getShowtimes() {
        StringBuilder showtimes = new StringBuilder();
        for (Map.Entry<String, String[]> entry : movies.entrySet()) {
            showtimes.append("\n").append(entry.getKey()).append(": ").append(String.join(", ", entry.getValue()));
        }
        return showtimes.toString();
    }
}
