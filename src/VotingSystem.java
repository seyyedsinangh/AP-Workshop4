import java.util.ArrayList;

public class VotingSystem {
    private ArrayList<Voting> votingList;

    public VotingSystem() {
        this.votingList = new ArrayList<Voting>();
    }

    public ArrayList<Voting> getVotingList() {
        return votingList;
    }

    public Voting getVoting(int index) {
        return votingList.get(index);
    }

    public void createVoting(String question, boolean isAnonymous, int type, ArrayList<String> choices) {
        Voting voting = new Voting(type,question,isAnonymous);
        for (String choice: choices) {
            voting.createChoices(choice);
        }
        votingList.add(voting);
    }

    public void printResults(int index) {
        System.out.println("Results for voting" + (index+1) + " {");
        votingList.get(index).printResults();
        System.out.println("}");
    }

    public void printVoters(int index) {
        System.out.println("Voters of the voting" + (index+1) + " {");
        votingList.get(index).printVoters();
        System.out.println("}");
    }

    public void printVoting(int index) {
        Voting voting = votingList.get(index);
        ArrayList<String> choices = voting.getChoices();
        System.out.println("Voting" + (index+1) + " {");
        System.out.println("\tQuestion: " + voting.getQuestion());
        int i = 1;
        for (String choice: choices) {
            System.out.println("\t" + (i++) + "." + choice);
        }
        System.out.println("}");
    }

    public void vote(int index, Person voter, String date) {
        votingList.get(index).vote(voter,date);
    }
}
