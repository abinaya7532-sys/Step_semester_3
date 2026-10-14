
import java.util.*;

interface ScoringRule {
    double calculate(double idea, double execution, double presentation);
}

class InnovationTrack implements ScoringRule {
    public double calculate(double idea, double execution, double presentation) {
        return idea * 0.5 + execution * 0.3 + presentation * 0.2;
    }
}

class OpenTrack implements ScoringRule {
    public double calculate(double idea, double execution, double presentation) {
        return (idea + execution + presentation) / 3.0;
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Project {
    String name;
    Team team;
    Score score;

    Project(String name, Team team) {
        this.name = name;
        this.team = team;
    }
}

class Score {
    double idea, execution, presentation;

    Score(double idea, double execution, double presentation) {
        this.idea = idea;
        this.execution = execution;
        this.presentation = presentation;
    }
}

class Team {
    String name;
    List<Student> members;
    ScoringRule track;
    Project project;

    Team(String name, List<Student> members, ScoringRule track) {
        this.name = name;
        this.members = members;
        this.track = track;
    }
}

class Hackathon {
    List<Team> teams = new ArrayList<>();
    Set<String> studentNames = new HashSet<>();
    String state = "Open";

    boolean register(Team team) {
        if (!state.equals("Open")) {
            System.out.println("Registration failed: Registration is closed.");
            return false;
        }

        if (team.members.size() < 2 || team.members.size() > 4) {
            System.out.println("Registration failed: A team must have 2 to 4 members.");
            return false;
        }

        for (Student s : team.members) {
            if (studentNames.contains(s.name)) {
                System.out.println("Registration failed: " + s.name + " already belongs to a team.");
                return false;
            }
        }

        for (Student s : team.members) {
            studentNames.add(s.name);
        }

        teams.add(team);
        System.out.println("Team " + team.name + " registered (" + team.members.size() + " members, " +
                (team.track instanceof InnovationTrack ? "Innovation" : "Open") + " track).");
        return true;
    }

    void submit(Team team, String projectName) {
        if (!state.equals("Open") || !teams.contains(team) || team.project != null) {
            System.out.println("Submission failed.");
            return;
        }

        team.project = new Project(projectName, team);
        System.out.println("Project '" + projectName + "' submitted by " + team.name + ".");
    }

    void score(Project project, double idea, double execution, double presentation) {
        if (state.equals("Published")) {
            System.out.println("Rescore rejected: Results have already been published.");
            return;
        }

        if (project == null || project.team.project != project ||
                idea < 0 || idea > 10 || execution < 0 || execution > 10 ||
                presentation < 0 || presentation > 10) {
            System.out.println("Score rejected.");
            return;
        }

        project.score = new Score(idea, execution, presentation);
        state = "Judging";
        System.out.println("Score recorded for '" + project.name + "'.");
    }

    void publishResults() {
        if (state.equals("Published")) {
            System.out.println("Results already published.");
            return;
        }

        for (Team team : teams) {
            if (team.project != null && team.project.score != null) {
                Score s = team.project.score;
                double result = team.track.calculate(s.idea, s.execution, s.presentation);
                System.out.printf("Final score: %.2f%n", result);
            }
        }

        state = "Published";
        System.out.println("Results published.");
    }
}

public class HackathonSystem {
    public static void main(String[] args) {
        Hackathon h = new Hackathon();

        Team byteBusters = new Team("ByteBusters",
                Arrays.asList(new Student("Asha"), new Student("Ravi"), new Student("Neha")),
                new InnovationTrack());

        Team soloCoder = new Team("SoloCoder",
                Arrays.asList(new Student("Kiran")), new OpenTrack());

        h.register(byteBusters);
        h.register(soloCoder);

        h.submit(byteBusters, "SmartAttend");
        h.score(byteBusters.project, 8, 7, 9);
        h.publishResults();
        h.score(byteBusters.project, 10, 7, 9);
    }
}
