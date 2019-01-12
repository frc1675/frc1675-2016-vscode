package org.usfirst.frc.team1675.robot.commands.clawarm;

import org.usfirst.frc.team1675.robot.Robot;
import org.usfirst.frc.team1675.robot.utils.ClearDuringDisable;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveClawArmWithAcceleration extends ClawArmMoveForXSeconds  {


	public MoveClawArmWithAcceleration(double clawControlTime, double power) {
		super(clawControlTime, power);

	}
	@Override 
	protected void execute() {
		Robot.clawArm.moveWithoutEncoderWithAcceleration(power); 
	}
	
	@Override
	public void end(){
		Robot.clawArm.clearTheBucket();
		super.end();
	}

	
}