package org.usfirst.frc.team1675.robot.commands.liftarm;

import org.usfirst.frc.team1675.robot.Robot;
import org.usfirst.frc.team1675.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveLiftArmToDown extends Command {

    public MoveLiftArmToDown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.liftArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.liftArm.moveArm(RobotMap.LiftArmConstants.LIFT_ARM_DOWN_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.liftArm.getLimitValueDown(); 
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.liftArm.moveArm(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
