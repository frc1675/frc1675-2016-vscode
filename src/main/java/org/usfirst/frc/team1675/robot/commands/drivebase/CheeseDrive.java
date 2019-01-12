package org.usfirst.frc.team1675.robot.commands.drivebase;

import org.usfirst.frc.team1675.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CheeseDrive extends Command {

    public CheeseDrive() {
    	
        requires(Robot.driveBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double turnPower = Robot.oi.getDriverRightXAxis();
    	double forwardPower = Robot.oi.getDriverLeftYAxis();
    	
    	double rightPower = forwardPower - turnPower;
    	double leftPower = forwardPower + turnPower;
    	
    	Robot.driveBase.setLeftMotorPower(leftPower);
    	Robot.driveBase.setRightMotorPower(rightPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
