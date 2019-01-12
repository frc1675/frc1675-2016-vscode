package org.usfirst.frc.team1675.robot.subsystems;

import org.usfirst.frc.team1675.robot.RobotMap;
import org.usfirst.frc.team1675.robot.commands.clawarm.MoveWithControllerAcceleration;
import org.usfirst.frc.team1675.robot.utils.AccelerationSpeedController;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;

import edu.wpi.first.wpilibj.command.Subsystem;


public class ClawArm extends Subsystem {

	private TalonSRX armMotor;
	private AccelerationSpeedController accelerationController;
	private DigitalInput upLimitSwitch;
	private DigitalInput downLimitSwitch;

	// Visibility (private) Type (SpeedController) Name (armMotor)

	public ClawArm(boolean isInverted) {
		armMotor = new TalonSRX(RobotMap.CANDeviceIDs.CLAW_ARM_MOTOR);
		accelerationController = new AccelerationSpeedController(armMotor,
				0.10, 160);
		armMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,0);
		armMotor.config_kP(0,RobotMap.ArmConstants.P, 0);
		armMotor.config_kI(0,RobotMap.ArmConstants.I, 0);
		armMotor.config_kD(0,RobotMap.ArmConstants.D, 0);

		armMotor.set(ControlMode.PercentOutput,0);
		upLimitSwitch = new DigitalInput(
				RobotMap.DIOChannels.ARM_UP_LIMIT_SWITCH);
		downLimitSwitch = new DigitalInput(
				RobotMap.DIOChannels.ARM_DOWN_LIMIT_SWITCH);
		armMotor.setInverted(isInverted);
	}

	public boolean getLimitValueUp() {
		return upLimitSwitch.get();

	}

	public boolean getLimitValueDown() {
		return downLimitSwitch.get();
	}

	public void moveArm(double speed) {

		
		double angle = armMotor.getSelectedSensorPosition(0);

		armMotor.set(ControlMode.PercentOutput,0);

		if (angle >= RobotMap.ArmConstants.MINIMUM
				&& angle <= RobotMap.ArmConstants.MAXIMUM) {
			moveWithinLimitSwitch(armMotor, speed);
		}
	}

	public void setPosition(double position) {

		//armMotor.changeControlMode(TalonControlMode.Position);
		armMotor.set(ControlMode.Position,position);

	}

	public void moveWithoutEncoder(double power) {
		
		moveWithinLimitSwitch(armMotor, power);
		
	}

	public void moveWithoutEncoderWithAcceleration(double power) {
		
		moveWithinLimitSwitch(accelerationController, power);
	}

	private void moveWithinLimitSwitch(TalonSRX sc, double power) {
		//stuff wired wrong on practice robot fix for competition
		if (getLimitValueUp() == true) {
			if (power < 0) {
				sc.set(sc.getControlMode(),power);
			} else {
				sc.set(sc.getControlMode(),0);
			}
		} else if (getLimitValueDown() == true) {
			if (power > 0) {
				sc.set(sc.getControlMode(),power);
			} else {
				sc.set(sc.getControlMode(),0);
			}
		} else {
			sc.set(sc.getControlMode(),power);
		}
	}
	private void moveWithinLimitSwitch(SpeedController sc, double power) {
		//stuff wired wrong on practice robot fix for competition
		if (getLimitValueUp() == true) {
			if (power < 0) {
				sc.set(power);
			} else {
				sc.set(0);
			}
		} else if (getLimitValueDown() == true) {
			if (power > 0) {
				sc.set(power);
			} else {
				sc.set(0);
			}
		} else {
			sc.set(power);
		}
	}

	public void initDefaultCommand() {

		// setDefaultCommand(new MoveWithController());
		setDefaultCommand(new MoveWithControllerAcceleration());
	}

	public double getPosition() {
		return armMotor.getSelectedSensorPosition(0);

	}
	
	public void clearTheBucket() {
		accelerationController.clearWhileDisabled();
	}

	public void stopAndDisable() {

		armMotor.set(ControlMode.PercentOutput,0); // change mode
		// to manual
		//armMotor.set(0);
	}
}
