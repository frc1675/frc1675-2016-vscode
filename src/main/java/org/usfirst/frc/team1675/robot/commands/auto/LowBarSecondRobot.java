package org.usfirst.frc.team1675.robot.commands.auto;

import org.usfirst.frc.team1675.robot.commands.Wait;
import org.usfirst.frc.team1675.robot.commands.clawarm.ClawArmMoveForXSeconds;
import org.usfirst.frc.team1675.robot.commands.drivebase.DriveForDistance;
import org.usfirst.frc.team1675.robot.commands.drivebase.TurnWithGyro;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarSecondRobot extends CommandGroup {
    
    public  LowBarSecondRobot() {
        
    	addSequential(new Wait(6.0));
    	addSequential(new DriveForDistance(-72.0));
    	addSequential(new ClawArmMoveForXSeconds(1, -0.3));
    	addSequential(new TurnWithGyro(65.0));
    	addSequential(new DriveForDistance(-144.0));
    	
    	
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
