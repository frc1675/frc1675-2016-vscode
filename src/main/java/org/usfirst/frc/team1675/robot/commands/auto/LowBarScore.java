package org.usfirst.frc.team1675.robot.commands.auto;

import org.usfirst.frc.team1675.robot.commands.Wait;
import org.usfirst.frc.team1675.robot.commands.claw.ClawIntakeForTime;
import org.usfirst.frc.team1675.robot.commands.claw.ClawOutputForTime;
import org.usfirst.frc.team1675.robot.commands.clawarm.ClawArmMoveForXSeconds;
import org.usfirst.frc.team1675.robot.commands.drivebase.DriveForDistance;
import org.usfirst.frc.team1675.robot.commands.drivebase.DriveStraightForTime;
import org.usfirst.frc.team1675.robot.commands.drivebase.TurnWithGyro;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarScore extends CommandGroup {
    
    public  LowBarScore() {
    	
    	addSequential(new ClawArmMoveForXSeconds(0.4, -0.4));
    	addSequential(new Wait(1.5));
    	addSequential(new DriveForDistance(219.0, 4.5));
    	addSequential(new TurnWithGyro(55.0, 2.5));
    	addSequential(new DriveForDistance(108.0, 3.5));
    	addSequential(new DriveWhileSpit(24.0, 2.0));
    	addSequential(new ClawIntakeForTime(1.0));
    	addSequential(new ClawOutputForTime(1.0));

    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
