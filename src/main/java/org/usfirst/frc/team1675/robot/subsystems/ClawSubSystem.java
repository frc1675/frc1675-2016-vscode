package org.usfirst.frc.team1675.robot.subsystems;

import org.usfirst.frc.team1675.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClawSubSystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private SpeedController rollerTop;
	private SpeedController rollerBottom;

	public ClawSubSystem(boolean isInverted) {
		rollerTop = new VictorSP(RobotMap.PWMChannels.CLAW_TOP_MOTOR);
		rollerBottom = new VictorSP(RobotMap.PWMChannels.CLAW_BOTTOM_MOTOR);
		rollerTop.setInverted(isInverted);
		rollerBottom.setInverted(isInverted);
	}

	public void setRollerInPower(double value) {
		clawSpin(value);
	}

	public void setRollerOutPower(double value) {
		clawSpin(value);
	}
	public void clawSpin(double power){
		rollerTop.set(power);
		rollerBottom.set(-power);
	}
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
