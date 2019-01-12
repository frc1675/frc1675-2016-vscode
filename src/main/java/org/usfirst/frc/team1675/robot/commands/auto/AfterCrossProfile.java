package org.usfirst.frc.team1675.robot.commands.auto;

import org.usfirst.frc.team1675.robot.commands.drivebase.DriveForDistance;
import org.usfirst.frc.team1675.robot.commands.drivebase.TurnWithGyro;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AfterCrossProfile extends CommandGroup {
    
    public  AfterCrossProfile(double x1, double x2, double x3, double angle1, double angle2) {
        
		addSequential(new DriveForDistance(x1, 4.0));
    	addSequential(new TurnWithGyro(angle1, 2.0));
    	addSequential(new DriveForDistance(x2, 4.0));
    	addSequential(new TurnWithGyro(angle2, 2.0));
    	addSequential(new DriveForDistance(x3, 4.0));
    	
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
