package org.usfirst.frc.team1675.robot.commands.drivebase;

import org.usfirst.frc.team1675.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnForTime extends Command {

	private double timeToWait;
	private Timer waitTimer;
	private double power;

    public TurnForTime(double power, double timeToWait) {
    	System.out.println("start turn");
    	requires(Robot.driveBase);
    	
    	this.timeToWait = timeToWait;
    	waitTimer = new Timer();
    	
    	this.power = power;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	waitTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("sending motor power");
    	Robot.driveBase.setLeftMotorPower(power);
    	Robot.driveBase.setRightMotorPower(-power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(waitTimer.get() < timeToWait){
    		return false;
    	}else{
    		return true;
    	}
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveBase.setLeftMotorPower(0);
    	Robot.driveBase.setRightMotorPower(0);
    	waitTimer.stop();
    	waitTimer.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
