package org.usfirst.frc.team1675.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Wait extends Command {
	private double waitTime;
	private Timer waitTimer;
	
	
    public Wait(double waitTime) {
    	this.waitTime = waitTime;
    	waitTimer = new Timer();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	

    	waitTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (waitTimer.get() < waitTime){
        	return false;
        }
        else{ 
        	return true;
       
        }
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	waitTimer.stop();
    	waitTimer.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
