package org.usfirst.frc.team1675.robot.commands.claw;

import org.usfirst.frc.team1675.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClawSpin extends Command {
	double motorSpeed;
	
	public ClawSpin(double power) {
		motorSpeed = power;		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.clawSub);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.clawSub.clawSpin(motorSpeed); // Set actual value later. 1 is a
											// filler
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.clawSub.setRollerInPower(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		this.end();
	}
}
