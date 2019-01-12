package org.usfirst.frc.team1675.robot.commands.auto;

import org.usfirst.frc.team1675.robot.commands.Wait;
import org.usfirst.frc.team1675.robot.commands.clawarm.ClawArmMoveForXSeconds;
import org.usfirst.frc.team1675.robot.commands.drivebase.DriveForDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarCross extends CommandGroup {
    
    public  LowBarCross() {
    	addSequential(new ClawArmMoveForXSeconds(0.4, -0.3));
    	addSequential(new Wait(1.5));
    	addSequential(new DriveForDistance(138.0, 3.0));
    }
}
