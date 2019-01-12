package org.usfirst.frc.team1675.robot.commands.auto;

import org.usfirst.frc.team1675.robot.commands.claw.ClawOutputForTime;
import org.usfirst.frc.team1675.robot.commands.drivebase.DriveForDistance;
import org.usfirst.frc.team1675.robot.commands.drivebase.DriveStraightForTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveWhileSpit extends CommandGroup {
    
    public  DriveWhileSpit(double inches, double timeout) {
    	//uses same logic from DriveThenDriveWhileShootingAuto branch
//        addParallel(new DriveForDistance(inches, timeout));
//        addSequential(new ClawOutputForTime(timeout));
    	addParallel(new DriveStraightForTime(.75, .5));
    	addSequential(new ClawOutputForTime(1.0));
    }
}
