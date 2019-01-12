package org.usfirst.frc.team1675.robot.commands.clawarm;

import org.usfirst.frc.team1675.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetArmPosition extends Command {

	private double position;

	public SetArmPosition(double position) {

		requires(Robot.clawArm);
		this.position = position;

	}

	protected void initialize() {

		Robot.clawArm.setPosition(position);
	}

	protected void execute() {

	}

	protected boolean isFinished() {
		return false;
		//loop
	}

	protected void end() {
		Robot.clawArm.stopAndDisable();
	}

	protected void interrupted() {
		this.end();
	}
}
