import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        VotingSystem myCountry = new VotingSystem();

        Voting presidentElection = new Voting(0, "who should be the persident?", false);
        presidentElection.createChoices("Mr.A in white suit.");
        presidentElection.createChoices("Mr.A in black suit.");
        ArrayList<String> presidentChoices = presidentElection.getChoices();

        Voting filteringElection = new Voting(1, "should we keep on filtering the internet?", false);
        filteringElection.createChoices("yes.");
        filteringElection.createChoices("yes of course!");
        filteringElection.createChoices("affirmative!");
        ArrayList<String> filteringChoices = filteringElection.getChoices();

        Voting holidaysElection = new Voting(0, "increase national holidays?", true);
        holidaysElection.createChoices("yes");
        holidaysElection.createChoices("no");
        ArrayList<String> holidayChoices = holidaysElection.getChoices();

        myCountry.createVoting(presidentElection.getQuestion(), presidentElection.isAnonymous, presidentElection.getType(), presidentElection.getChoices());
        myCountry.createVoting(filteringElection.getQuestion(), filteringElection.isAnonymous, filteringElection.getType(), filteringElection.getChoices());
        myCountry.createVoting(holidaysElection.getQuestion(), holidaysElection.isAnonymous, holidaysElection.getType(), holidaysElection.getChoices());

        Person farbod = new Person("farbod", "bijary");
        Person sina = new Person("seyyed sina", "nagahban");
        Person zeinal = new Person("Dr", "zeinali");
        Person kashani = new Person("Mr", "kashani");
        Person mamad = new Person("mamad", "hamidy");
        Person ANON = new Person("anonymous", "anonymous");

        ArrayList<Person> People = new ArrayList<>();
        People.add(farbod);
        People.add(sina);
        People.add(zeinal);
        People.add(kashani);
        People.add(mamad);

        ArrayList<ArrayList<String>> listOfAllchoices = new ArrayList<>();
        listOfAllchoices.add(presidentChoices);
        listOfAllchoices.add(filteringChoices);
        listOfAllchoices.add(holidayChoices);

        Scanner scanner = new Scanner(System.in);
        myCountry.showAllVotings();
        System.out.println("enter the voting index of your choice: ");
        int votingIndex = Integer.parseInt(scanner.nextLine()) - 1;
        TestVotingSystem.randomVote(myCountry, votingIndex, listOfAllchoices, People);
        myCountry.printVoters(votingIndex);
        myCountry.printResults(votingIndex);

    }

}


class TestVotingSystem {
    static void randomVote(VotingSystem currentVotingSystem, int votingIndex, ArrayList<ArrayList<String>> choicesList, ArrayList<Person> People) {
        Random RandGen = new Random();
        ArrayList<String> choices = choicesList.get(votingIndex);
        ArrayList<String> choice = new ArrayList<String>();
        for(Person person: People) {
            choice.add(choices.get(RandGen.nextInt(choices.size())));
            currentVotingSystem.getVoting(votingIndex).vote(person, LocalDate.now().toString(), choice);
            choice.clear();
        }
    }
}
