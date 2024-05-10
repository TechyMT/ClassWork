import java.util.Scanner;

public class MedicalDiagnosis {

    // Function to ask yes/no questions
    public static boolean askQuestion(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(question + " (y/n): ");
        char response = scanner.next().charAt(0);
        return (response == 'y' || response == 'Y');
    }

    // Function to diagnose allergies
    public static boolean diagnoseAllergies() {
        boolean itchingOrSwelling = askQuestion("Do you experience any itching or swelling?");
        boolean redness = askQuestion("Do you have red, watery eyes?");
        return (itchingOrSwelling || redness);
    }

    // Function to diagnose fever
    public static boolean diagnoseFever() {
        boolean highTemperature = askQuestion("Do you have a temperature above 37.5°C?");
        boolean chills = askQuestion("Do you experience chills?");
        return (highTemperature || chills);
    }

    // Function to diagnose cold
    public static boolean diagnoseCold() {
        boolean runnyOrStuffyNose = askQuestion("Do you have a runny or stuffy nose?");
        boolean sneezing = askQuestion("Are you sneezing frequently?");
        return (runnyOrStuffyNose || sneezing);
    }

    // Function to diagnose flu
    public static boolean diagnoseFlu() {
        boolean bodyAches = askQuestion("Do you have body aches?");
        boolean fatigue = askQuestion("Do you feel tired or fatigued?");
        boolean highTemperature = askQuestion("Do you have a temperature above 38°C?");
        return (bodyAches && fatigue && highTemperature);
    }

    // Function to diagnose strep throat
    public static boolean diagnoseStrepThroat() {
        boolean soreThroat = askQuestion("Do you have a sore throat?");
        boolean swollenTonsils = askQuestion("Are your tonsils swollen?");
        return (soreThroat && swollenTonsils);
    }

    // Function to diagnose food poisoning
    public static boolean diagnoseFoodPoisoning() {
        boolean nausea = askQuestion("Do you feel nauseous?");
        boolean vomiting = askQuestion("Have you been vomiting?");
        boolean diarrhea = askQuestion("Do you have diarrhea?");
        return (nausea && vomiting && diarrhea);
    }

    // Function to diagnose appendicitis
    public static boolean diagnoseAppendicitis() {
        boolean severeAbdominalPain = askQuestion("Do you have severe abdominal pain?");
        boolean lossOfAppetite = askQuestion("Have you lost your appetite?");
        return (severeAbdominalPain && lossOfAppetite);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Expert System for Medical Diagnosis\n");

        boolean hasAllergies = diagnoseAllergies();
        boolean hasFever = diagnoseFever();
        boolean hasCold = diagnoseCold();
        boolean hasFlu = diagnoseFlu();
        boolean hasStrepThroat = diagnoseStrepThroat();
        boolean hasFoodPoisoning = diagnoseFoodPoisoning();
        boolean hasAppendicitis = diagnoseAppendicitis();

        System.out.println("\nDiagnosis:");

        if (hasAllergies) {
            System.out.println("You may have allergies.");
        }
        if (hasFever) {
            System.out.println("You may have a fever.");
        }
        if (hasCold) {
            System.out.println("You may have a cold.");
        }
        if (hasFlu) {
            System.out.println("You may have flu.");
        }
        if (hasStrepThroat) {
            System.out.println("You may have a throat infection.");
        }
        if (hasFoodPoisoning) {
            System.out.println("You may have food poisoning.");
        }
        if (hasAppendicitis) {
            System.out.println("You may have appendicitis.");
        }

        if (!hasAllergies && !hasFever && !hasCold && !hasFlu && !hasStrepThroat && !hasFoodPoisoning && !hasAppendicitis) {
            System.out.println("No specific diagnosis could be made based on the provided symptoms.");
        }
    }

}
