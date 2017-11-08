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
public class FootballClub extends SportsClub implements Comparable<FootballClub> {

    private int Wins, Draws, Losses, GoalsConceded, GoalsScored, Points, MatchesPlayed;

    public FootballClub(String name, String location){
        Name = name;
        Location = location;
    }

    public int getWins(){
        return Wins;
    }

    public int getDraws(){
        return Draws;
    }

    public int getLosses(){
        return Losses;
    }

    public int getGoalsConceded(){
        return GoalsConceded;
    }

    public int getGoalsScored(){
        return GoalsScored;
    }

    public int getPoints(){
        return Points;
    }

    public int getMatchesPlayed(){
        return MatchesPlayed;
    }

    public int getGoalDifference(){
        return (GoalsScored - GoalsConceded);
    }

    // This method is used to add a match in to the system for a specific team.
    public void addMatch(int goalsScored, int goalsConceded){
        GoalsScored += goalsScored;
        GoalsConceded += goalsConceded;
        MatchesPlayed++;
        if(goalsScored > goalsConceded){
            Wins++;
            Points += 3;
        } else if (goalsScored == goalsConceded){
            Draws++;
            Points += 1;
        } else if (goalsScored < goalsConceded){
            Losses++;
            Points += 0;
        }

    }

    // This is used to sort the one FootballClub object to another by the number of points
    @Override
    public int compareTo(FootballClub o) {
        if(Points < o.Points){
            return 1;
        } else if (Points > o.Points) {
            return -1;
        } else {
            return compareGoalDiff(o);
        }
    }

    // This is used to sort the FootballClub object by the goal difference between them.
    private int compareGoalDiff(FootballClub o){
        if(getGoalDifference() < (o.getGoalDifference())){
            return 1;
        } else {
            return -1;
        }
    }
}


