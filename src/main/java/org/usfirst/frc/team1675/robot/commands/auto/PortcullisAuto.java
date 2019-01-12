package org.usfirst.frc.team1675.robot.commands.auto;

import org.usfirst.frc.team1675.robot.commands.Wait;
import org.usfirst.frc.team1675.robot.commands.clawarm.ClawArmMoveForXSeconds;
import org.usfirst.frc.team1675.robot.commands.drivebase.DriveForDistance;
import org.usfirst.frc.team1675.robot.commands.drivebase.DriveStraightForTime;
import org.usfirst.frc.team1675.robot.commands.liftarm.MoveLiftArmToDown;
import org.usfirst.frc.team1675.robot.commands.liftarm.MoveLiftArmToHome;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PortcullisAuto extends CommandGroup {
    
    public  PortcullisAuto() {
    	
    	addSequential(new DriveForDistance(-24.0, 1.0));
    	addSequential(new ClawArmMoveForXSeconds(0.4, -0.3));//Lower claw arm here will go
    	addSequential(new MoveLiftArmToDown());
    	//addSequential(new DriveForDistance(-96.0));
    	addSequential(new DriveStraightForTime(-0.6, 2.0));
    	addSequential(new DriveStraightForTime(0.3, 0.1));
    	addSequential(new MoveLiftArmToHome());
    	
    	
//    	addSequential(new Wait(1.5));
//    	addSequential(new MoveLiftArmToDown());
//    	addSequential(new DriveForDistance(-60.0));
//    	addParallel(new MoveLiftArmToHome());
//    	addSequential(new DriveForDistance(-80.0));
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
