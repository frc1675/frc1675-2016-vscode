package org.usfirst.frc.team1675.robot.commands.clawarm;

import org.usfirst.frc.team1675.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ClawArmMoveForXSeconds extends Command {
	private double clawControlTime;
	private Timer clawControlTimer;
	protected double power;

	public ClawArmMoveForXSeconds(double clawControlTime, double power) {
		requires(Robot.clawArm);
		clawControlTimer = new Timer();
		this.clawControlTime = clawControlTime;
		this.power = power;
	}

	protected void initialize() {
		clawControlTimer.start();

	}

	protected void execute() {

		Robot.clawArm.moveWithoutEncoder(power);

	}

	protected boolean isFinished() {
		if (clawControlTimer.get() < clawControlTime) {
			return false;
		} else {
			return true;
		}
	}

	protected void end() {
		clawControlTimer.stop();
		clawControlTimer.reset();
		Robot.clawArm.moveWithoutEncoder(0);

	}

	protected void interrupted() {
		this.end();

	}


}
