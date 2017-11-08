/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package premierleaguemanager;

import java.util.GregorianCalendar;

/**
 *
 * @author AbelGezahegn
 */
public class MatchInfo {

    private GregorianCalendar MatchDate;
    private String Team1name;
    private String Team2name;
    private int GoalsScored;
    private int GoalsConceded;

    public MatchInfo(String team1name, String team2name, int goalsScored, int goalsConceded, GregorianCalendar matchDate){
        Team1name = team1name;
        Team2name = team2name;
        GoalsConceded = goalsConceded;
        GoalsScored = goalsScored;
        MatchDate = matchDate;
    }

    public GregorianCalendar getMatchDate(){
        return MatchDate;
    }

    public String getTeam1name(){
        return Team1name;
    }

    public String getTeam2name(){
        return Team2name;
    }

    public int getGoalsScored(){
        return GoalsScored;
    }

    public int getGoalsConceded(){
        return GoalsConceded;
    }

}
