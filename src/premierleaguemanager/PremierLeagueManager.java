/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package premierleaguemanager;

/**
 *
 * @author AbelGezahegn
 */
import java.util.*;

public class PremierLeagueManager implements LeagueManager {

    private ArrayList<FootballClub> footballClubs;
    private ArrayList<MatchInfo> matchInfo;
    Scanner scanner = new Scanner(System.in);

    // Constructor to initiate the two ArrayLists.
    public PremierLeagueManager() {
        footballClubs = new ArrayList<>();
        matchInfo = new ArrayList<>();
    }

    // A method to show the menu and to get the input from the user
    public void menu(){
        boolean running = true;
        System.out.println("Choose from the following menu: ");
        System.out.println("1. Enter 'Create' to create a new club and add it to the league\n" +
                "2. Enter 'Delete' to delete an existing club from the league\n" +
                "3. Enter 'Statistics' to display a list of clubs to choose from to see their statistics\n" +
                "4. Enter 'Table' to display the Premier League Standings Table\n" +
                "5. Enter 'Add a match' to add a match into the system\n" +
                "6. Enter 'Calendar' to display the calendar\n" +
                "7. Enter 'Test' to add test data\n" +
                "8. Enter 'Menu' to see this menu again\n" +
                "9. Enter 'Exit' to exit the programme. All data will be lost.");

        while(running) {

            String input = scanner.nextLine();
            switch (input.toLowerCase()) {
                case "create":
                    createTeam();
                    break;
                case "delete":
                    deleteTeam();
                    break;
                case "statistics":
                    displayStatistics();
                    break;
                case "table":
                    displayTable();
                    break;
                case "add":
                    addMatch();
                    break;
                case "calendar":
                    showCalendar();
                    break;
                case "test":
                    addTestElements();
                    break;
                case "menu":
                    menu();
                    break;
                case "exit":
                    running = false;
                    break;
            }
        }
    }

    // This method is used to add a new team in to the array list
    public void createTeam() {
        System.out.println("Enter the name for the club: ");
        String name = scanner.nextLine();
        System.out.println("Enter the location for the club: ");
        String location = scanner.nextLine();

        // This for loop is a verification step to make sure that a club doesn't already exist.
        for(FootballClub fc : footballClubs){
            if(fc.getName().toLowerCase().equals(name.toLowerCase())){
                System.out.println("The club with the name " + name + " already exists.");
                return;
            }
        }

        footballClubs.add(new FootballClub(name, location));
    }

    // This method is used to delete team from the array list
    public void deleteTeam() {
        System.out.println("Enter the name of the club you want to delete: ");
        String name = scanner.nextLine().toLowerCase();
        for(FootballClub fc : footballClubs){
            if(fc.getName().toLowerCase().equals(name)){
                footballClubs.remove(fc);
                System.out.println("Team deleted successfully!");
            }
        }
    }

    // This method is used to display statistics for a specific team
    public void displayStatistics() {
        System.out.println("Type the name of the team you want to see the statistics for: ");
        String name = scanner.nextLine().toLowerCase();

        for (FootballClub fc : footballClubs) {
            if (name.equals(fc.getName().toLowerCase())) {
                System.out.println("Name: " + fc.getName());
                System.out.println("Location: " + fc.getLocation());
                System.out.println("Matches Played: " + fc.getMatchesPlayed());
                System.out.println("Wins: " + fc.getWins());
                System.out.println("Draws: " + fc.getDraws());
                System.out.println("Losses: " + fc.getLosses());
                System.out.println("Goals Scored: " + fc.getGoalsScored());
                System.out.println("Goals Conceded: " + fc.getGoalsConceded());
                System.out.println("Points: " + fc.getPoints());
            }
        }
    }

    // This method is used to display the league table
    public void displayTable() {
        // This line uses the Collections library to sort the ArrayList.
        Collections.sort(footballClubs);

        // This line formats the text so that each column has a specified width.
        System.out.format("%20s%20s%10s%10s%10s%10s%10s%10s%10s%10s\n","Name","Location","Matches","Wins","Draws","Losses","Points","Scored","Conceded","Goal Diff");
        for(FootballClub fc : footballClubs){
            System.out.format("%20s%20s%10d%10d%10d%10d%10d%10d%10d%10d\n",fc.getName(),fc.getLocation(),fc.getMatchesPlayed(),fc.getWins(),fc.getDraws(),fc.getLosses(),fc.getPoints(),fc.getGoalsScored(),fc.getGoalsConceded(),fc.getGoalDifference());
        }
    }

    // This method is used to add a match into the system
    public void addMatch() {
        System.out.println("Enter team name for home team: ");
        String team1 = scanner.nextLine();
        System.out.println("Enter team name for away team: ");
        String team2 = scanner.nextLine();

        boolean team1exist = false, team2exist = false;
        for(FootballClub fc : footballClubs){
           if(fc.getName().toLowerCase().equals(team1.toLowerCase())){
               team1exist = true;
               team1 = fc.getName();
           } else if(!fc.getName().equals(team1) && !team1exist){
               team1exist = false;
           }
        }

        for(FootballClub fc : footballClubs){
            if(fc.getName().toLowerCase().equals(team2.toLowerCase())){
                team2exist = true;
                team2 = fc.getName();
            } else if(!fc.getName().equals(team2) && !team2exist){
                team2exist = false;
            }
        }

        if((!team1exist) || (!team2exist)){
            System.out.println("One of the teams does not exist.");
            return;
        }

        System.out.println("Enter the goals scored by team 1: ");
        int team1Goals = scanner.nextInt();

        System.out.println("Enter the goals scored by team 2: ");
        int team2Goals = scanner.nextInt();

        System.out.println("Enter the date of the match: DD/MM/YYYY");
        String dateString = scanner.next();

        int year = Integer.parseInt(dateString.substring(6,10));
        int month = Integer.parseInt(dateString.substring(3,5));
        int date = Integer.parseInt(dateString.substring(0,2));

        GregorianCalendar calendar = new GregorianCalendar(year, month, date);

        matchInfo.add(new MatchInfo(team1, team2, team1Goals, team2Goals, calendar));

        for (FootballClub fc : footballClubs) {
            if (fc.getName().toLowerCase().equals(team1.toLowerCase())) {
                fc.addMatch(team1Goals, team2Goals);
            }
            if (fc.getName().toLowerCase().equals(team2.toLowerCase())) {
                fc.addMatch(team2Goals, team1Goals);
            }
        }
    }

    // This method is used to show the calendar and then see if any matches happened on a specific date
    public void showCalendar() {
        System.out.println("Enter the month (Jan = 1, Dec = 12) you want to see the calendar for: ");
        int month = scanner.nextInt() - 1;

        System.out.println("Enter the year (YYYY) you want to see the calendar for: ");
        int year = scanner.nextInt();

        Calendar calendar = new GregorianCalendar();

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        int firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        String[] monthName = {"January","February","March","April","May","June","July","August","September","October","November","December"};

        System.out.println("\t\t" + monthName[month] + " " + year);
        System.out.println("Su\tMo\tTu\tWe\tTh\tFr\tSa");

        int daysInWeek = 0;
        for(int i = 1; i < firstDayOfMonth; i++){
            System.out.print("\t");
            daysInWeek++;
        }

        for(int i = 1; i <= daysInMonth; i++){
            System.out.format("%1$2d \t",i);
            daysInWeek++;

            if(daysInWeek == 7){
                daysInWeek = 0;
                System.out.println();
            }
        }

        System.out.println();
        System.out.println("Enter the date for which you would like to see a match for: ");
        int date = scanner.nextInt();

        System.out.println("On " + date + " " + monthName[month] + " " + year + " the following matches were played: ");

        for(MatchInfo mi : matchInfo){
            if(mi.getMatchDate().get(Calendar.DAY_OF_MONTH)==date){
                System.out.println("Game: " + mi.getTeam1name() + " vs " + mi.getTeam2name() + " and the score was " + mi.getGoalsScored() + "-" + mi.getGoalsConceded());
            }
        }
    }

    // This method is used as a dummy testing element. This adds test information into the system.
    public void addTestElements() {
        footballClubs.add(new FootballClub("Chelsea", "Stamford Bridge"));
        footballClubs.add(new FootballClub("Manchester United", "Old Trafford"));
        footballClubs.add(new FootballClub("Arsenal", "Emirates Stadium"));
        footballClubs.add(new FootballClub("Newcastle United", "St James Park"));
        footballClubs.add(new FootballClub("Manchester City", "City of Manchester"));
        footballClubs.add(new FootballClub("Liverpool", "Anfield"));
        footballClubs.add(new FootballClub("Aston Villa", "London"));
        footballClubs.add(new FootballClub("Everton", "Goodison Park"));
        footballClubs.add(new FootballClub("Tottenham Hotspur", "White Hart Lane"));
        footballClubs.add(new FootballClub("West Ham United", "Boleyn Ground"));

        for(FootballClub team1 : footballClubs){
            Random random = new Random();
            int team1Goals = random.nextInt(3) + 1;
            int team2Goals = random.nextInt(3) + 1;
            for(FootballClub team2 : footballClubs){
                team2.addMatch(team2Goals, team1Goals);
            }
            team1.addMatch(team1Goals, team2Goals);
        }
    }

}

