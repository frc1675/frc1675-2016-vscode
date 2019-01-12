package org.usfirst.frc.team1675.robot.utils;

import org.usfirst.frc.team1675.robot.commands.auto.AfterCrossProfile;
import org.usfirst.frc.team1675.robot.commands.auto.DriveWhileSpit;
import org.usfirst.frc.team1675.robot.commands.auto.FrenchRampsAuto;
import org.usfirst.frc.team1675.robot.commands.auto.LowBarCross;
import org.usfirst.frc.team1675.robot.commands.auto.LowBarScore;
import org.usfirst.frc.team1675.robot.commands.auto.PortcullisAuto;
import org.usfirst.frc.team1675.robot.commands.auto.RockWallAuto;
import org.usfirst.frc.team1675.robot.commands.auto.RoughTerrainAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoChooser {
	
	private SendableChooser defenseChooser;
	private SendableChooser positionChooser;
	private SendableChooser afterCrossChooser;
	private SendableChooser afterScoreChooser;
	
	public enum Defense {
		LOW_BAR_STOP,
		CDF,
		PORTCULLIS,
		ROCK_WALL,
		ROUGH_TERRAIN,
		LOW_BAR_SCORE
	}
	
	public enum Position {
		TWO,
		THREE,
		FOUR,
		FIVE
	}
	
	public enum AfterCrossChoice {
		NOTHING,
		SCORE_LEFT,
		SCORE_RIGHT,
		DRIVE_LEFT,
		DRIVE_RIGHT
	}
	
	public enum AfterCrossDirective {
		LEFT_FORWARDS,
		RIGHT_FORWARDS,
		LEFT_BACKWARDS,
		RIGHT_BACKWARDS
	}
	
	public enum AfterScore {
		NOTHING,
		SPIT_SUCK,
		DRIVE_TO_1,
		DRIVE_TO_2,
		DRIVE_TO_3,
		DRIVE_TO_4,
		DRIVE_TO_5
	}
	
	private AutoProfiles profiles = new AutoProfiles();
	
	public AutoChooser(){
		//make and fill in choosers with enums
		
		//put choosers on smart dashboard
		defenseChooser = new SendableChooser();
		positionChooser = new SendableChooser();
		afterCrossChooser = new SendableChooser();
//		afterScoreChooser = new SendableChooser();
		
		defenseChooser.addObject("Low Bar and Stop", Defense.LOW_BAR_STOP);
		defenseChooser.addObject("Shovel Fries", Defense.CDF);
		defenseChooser.addObject("Portcullis", Defense.PORTCULLIS);
		defenseChooser.addObject("Rock Wall", Defense.ROCK_WALL);
		defenseChooser.addObject("Rough Terrain", Defense.ROUGH_TERRAIN);
		defenseChooser.addObject("$$!Low Bar Score!$$", Defense.LOW_BAR_SCORE);
		
		positionChooser.addObject("2", Position.TWO);
		positionChooser.addObject("3", Position.THREE);
		positionChooser.addObject("4", Position.FOUR);
		positionChooser.addObject("5", Position.FIVE);
		
		afterCrossChooser.addObject("Nothing", AfterCrossChoice.NOTHING);
		afterCrossChooser.addObject("Score Left", AfterCrossChoice.SCORE_LEFT);
		afterCrossChooser.addObject("Score Right", AfterCrossChoice.SCORE_RIGHT);
		afterCrossChooser.addObject("Drive Left", AfterCrossChoice.DRIVE_LEFT);
		afterCrossChooser.addObject("Drive Right", AfterCrossChoice.DRIVE_RIGHT);
		
//		afterScoreChooser.addObject("Nothing", AfterScore.NOTHING);
//		afterScoreChooser.addObject("Spit Suck", AfterScore.SPIT_SUCK);
//		afterScoreChooser.addObject("Drive To 1", AfterScore.DRIVE_TO_1);
//		afterScoreChooser.addObject("Drive To 2", AfterScore.DRIVE_TO_2);
//		afterScoreChooser.addObject("Drive To 3", AfterScore.DRIVE_TO_3);
//		afterScoreChooser.addObject("Drive To 4", AfterScore.DRIVE_TO_4);
//		afterScoreChooser.addObject("Drive To 5", AfterScore.DRIVE_TO_5);
		
		SmartDashboard.putData("Defense", defenseChooser);
		SmartDashboard.putData("Position", positionChooser);
		SmartDashboard.putData("After Cross", afterCrossChooser);
//		SmartDashboard.putData("After Score", afterScoreChooser);
	}
	
	public CommandGroup generateAuto(){
		CommandGroup auto = new CommandGroup();
		
		Defense selectedDefense = (Defense) defenseChooser.getSelected();
		
		//EARLY EXIT - IF WE CHOOSE LOW BAR SCORE JUST DO THE OLDIE
		if(selectedDefense == Defense.LOW_BAR_SCORE){
			auto = new LowBarScore();
			return auto;
		}
		
		boolean robotBackwards = false;
		switch(selectedDefense){
		case LOW_BAR_STOP:
			robotBackwards = false;
			auto.addSequential(new LowBarCross());
			break;
		case CDF:
			robotBackwards = false;
			auto.addSequential(new FrenchRampsAuto());
			break;
		case PORTCULLIS:
			robotBackwards = true;
			auto.addSequential(new PortcullisAuto());
			break;
		case ROCK_WALL:
			robotBackwards = false;
			auto.addSequential(new RockWallAuto());
			break;
		case ROUGH_TERRAIN:
			robotBackwards = true;
			auto.addSequential(new RoughTerrainAuto());
			break;
		default:
			//shouldnt be possible, do nothing
			return auto;
		}
		
		Position selectedPosition = (Position) positionChooser.getSelected();
		AfterCrossChoice selectedAfterCross = (AfterCrossChoice) afterCrossChooser.getSelected();
		
		AutoProfiles.AutoProfile profile = null;
		boolean scoreAfterMove = true;
		switch(selectedAfterCross){
		case DRIVE_LEFT:
			scoreAfterMove = false;
		case SCORE_LEFT:
			if(selectedDefense == Defense.LOW_BAR_STOP){
				//do nothing
			} else if(robotBackwards){
				profile = profiles.getProfile(selectedPosition, AfterCrossDirective.LEFT_BACKWARDS);
			} else {
				profile = profiles.getProfile(selectedPosition, AfterCrossDirective.LEFT_FORWARDS);
			}
			break;
		case DRIVE_RIGHT:
			scoreAfterMove = false;
		case SCORE_RIGHT:
			if(selectedDefense == Defense.LOW_BAR_STOP){
				//are do nothing
			} else if(robotBackwards){
				profile = profiles.getProfile(selectedPosition, AfterCrossDirective.RIGHT_BACKWARDS);
			} else {
				profile = profiles.getProfile(selectedPosition, AfterCrossDirective.RIGHT_FORWARDS);
			}
			break;
		case NOTHING:
			//don't make a profile
		}
		
		if(profile != null){
			auto.addSequential(new AfterCrossProfile(
					profile.x1, 
					profile.x2, 
					profile.x3, 
					profile.angle1, 
					profile.angle2));
			
			//only add a score if we moved anyway
			if(scoreAfterMove){
				auto.addSequential(new DriveWhileSpit(24.0, 2.0));
			}
		}
		
		return auto;
	}

}
