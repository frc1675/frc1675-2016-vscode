package org.usfirst.frc.team1675.robot.subsystems;

import org.usfirst.frc.team1675.robot.RobotMap;
import org.usfirst.frc.team1675.robot.commands.liftarm.MoveLiftArmManual;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LiftArm extends Subsystem {

	private SpeedController armMotor;
	private DigitalInput upLimitSwitch;
	private DigitalInput downLimitSwitch;

	public LiftArm() {
		armMotor = new VictorSP(RobotMap.PWMChannels.LIFTER_MOTOR);
		armMotor.setInverted(true);
		upLimitSwitch = new DigitalInput(RobotMap.DIOChannels.LIFTER_UP_LIMIT_SWITCH);
		downLimitSwitch = new DigitalInput(
				RobotMap.DIOChannels.LIFTER_DOWN_LIMIT_SWITCH);
	}

	public boolean getLimitValueUp() {
		return upLimitSwitch.get();
	}

	public boolean getLimitValueDown() {
		return downLimitSwitch.get();
	}

	public void moveArm(double power) {
		SmartDashboard.putBoolean("Lifter Up", getLimitValueUp());
		SmartDashboard.putBoolean("Lifter Down", getLimitValueDown());

		if (getLimitValueUp() == true) {
			if (power < 0) {
				armMotor.set(power);
			} else {
				armMotor.set(0);
			}
		} else if (getLimitValueDown() == true) {
			if (power > 0) {
				armMotor.set(power);
			} else {
				armMotor.set(0);
			}
		} else {
			armMotor.set(power);
		}

		// if (getLimitValueUp() == true ){
		//
		// if(power > 0 ){
		// armMotor.set(power);
		// }else{
		// armMotor.set(0);
		// }
		//
		// }else if(getLimitValueDown() == true){
		//
		// if(power < 0){
		// armMotor.set(power);
		// }else{
		// armMotor.set(0);
		// }
		//
		// }
		// else {
		// armMotor.set(power);
		//
		// }
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {

		setDefaultCommand(new MoveLiftArmManual());

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
