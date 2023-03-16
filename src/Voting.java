import java.util.*;

public class Voting {
    private int type;
    private String question;
    private ArrayList<Person> voters;
    private HashMap<String, HashSet<Vote>> choices;
    boolean isAnonymous;

    public Voting(int type, String question, boolean isAnonymous) {
        this.type = type;
        this.question = question;
        this.isAnonymous = isAnonymous;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public HashMap<String, HashSet<Vote>> getChoices() {
        return choices;
    }


    public void createChoices(String choice) {
        HashSet<Vote> votes = new HashSet<Vote>();
        this.choices.put(choice, votes);
    }

    public void printVotes() {
        for(String choice: this.choices.keySet()){
            System.out.println(choice);
            for(Vote voter: this.choices.get(choice)){
                System.out.println("first name: " + voter.getVoter().getFirstName() + "\n" + "last name: " + voter.getVoter().getLastName());
                System.out.println("date of vote: " + voter.getDate());
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voting voting = (Voting) o;
        return type == voting.type && isAnonymous == voting.isAnonymous && question.equals(voting.question) && Objects.equals(voters, voting.voters) && choices.equals(voting.choices);
    }


    public void vote(Person voter, String date, ArrayList<String> voter_choices) {
        Vote newVote = new Vote(voter,date);
        voters.add(voter);
        for (String choice: voter_choices) {
            choices.get(choice).add(newVote);
        }
    }

    public void vote(Person person, String date) {
        Vote newVote = new Vote(person,date);
        voters.add(person);
        Random generator = new Random();
        String[] keyArr = choices.keySet().toArray(new String[0]);
        choices.get(keyArr[generator.nextInt(keyArr.length)]).add(newVote);
    }

    public void printResults() {
        for (String choice: choices.keySet()) {
            System.out.println(choice + ":" + choices.get(choice).size());
        }
    }

}
