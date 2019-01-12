package org.usfirst.frc.team1675.robot;

import org.usfirst.frc.team1675.robot.commands.liftarm.MoveLiftArmToDown;
import org.usfirst.frc.team1675.robot.commands.liftarm.MoveLiftArmToHome;
import org.usfirst.frc.team1675.robot.commands.Wait;
import org.usfirst.frc.team1675.robot.commands.claw.ClawIdle;
import org.usfirst.frc.team1675.robot.commands.claw.ClawSpin;
import org.usfirst.frc.team1675.robot.commands.clawarm.ClawArmMoveForXSeconds;
import org.usfirst.frc.team1675.robot.commands.drivebase.SwitchDriveMode;
import org.usfirst.frc.team1675.robot.commands.drivebase.TurnWithGyro;
import org.usfirst.frc.team1675.robot.utils.DPadButton;
import org.usfirst.frc.team1675.robot.utils.TriggerButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {


	private Joystick driverController = new Joystick(XBoxControllerMap.DRIVER_CONTROLLER_PORT);
	private JoystickButton driverAButton = new JoystickButton(driverController, XBoxControllerMap.A_BUTTON);
	private JoystickButton driverBButton = new JoystickButton(driverController, XBoxControllerMap.B_BUTTON);
	private JoystickButton driverYButton = new JoystickButton(driverController, XBoxControllerMap.Y_BUTTON);
	private JoystickButton driverXButton = new JoystickButton(driverController, XBoxControllerMap.X_BUTTON);
	private DPadButton driverDPadRight = new DPadButton(driverController, DPadButton.Direction.RIGHT);
	private DPadButton driverDPadLeft = new DPadButton(driverController, DPadButton.Direction.LEFT);
	private DPadButton driverDPadUp = new DPadButton(driverController, DPadButton.Direction.UP);
	private DPadButton driverDPadDown = new DPadButton(driverController, DPadButton.Direction.DOWN);
	private JoystickButton driverRightBumper = new JoystickButton(driverController, XBoxControllerMap.RIGHT_BUMPER_BUTTON);
	private JoystickButton driverLeftBumper = new JoystickButton(driverController, XBoxControllerMap.LEFT_BUMPER_BUTTON);
	

	private TriggerButton driverRightTrigger = new TriggerButton(driverController, true, RobotMap.DriverConstants.TRIGGER_DEAD_ZONE);
	private TriggerButton driverLeftTrigger = new TriggerButton(driverController, false, RobotMap.DriverConstants.TRIGGER_DEAD_ZONE);

	
	
	private Joystick operatorController = new Joystick(XBoxControllerMap.OPERATOR_CONTROLLER_PORT);
	private JoystickButton operatorAButton = new JoystickButton(operatorController, XBoxControllerMap.A_BUTTON);
	private JoystickButton operatorBButton = new JoystickButton(operatorController, XBoxControllerMap.B_BUTTON);
	private JoystickButton operatorYButton = new JoystickButton(operatorController, XBoxControllerMap.Y_BUTTON);
	private JoystickButton operatorXButton = new JoystickButton(operatorController, XBoxControllerMap.X_BUTTON);
	private DPadButton operatorDPadRight = new DPadButton(operatorController, DPadButton.Direction.RIGHT);
	private DPadButton operatorDPadLeft = new DPadButton(operatorController, DPadButton.Direction.LEFT);
	private DPadButton operatorDPadUp = new DPadButton(operatorController, DPadButton.Direction.UP);
	private DPadButton operatorDPadDown = new DPadButton(operatorController, DPadButton.Direction.DOWN);
	private JoystickButton operatorRightBumper = new JoystickButton(operatorController, XBoxControllerMap.RIGHT_BUMPER_BUTTON);
	private JoystickButton operatorLeftBumper = new JoystickButton(operatorController, XBoxControllerMap.LEFT_BUMPER_BUTTON);

	private TriggerButton operatorRightTrigger = new TriggerButton(operatorController, true, RobotMap.DriverConstants.TRIGGER_DEAD_ZONE);
	private TriggerButton operatorLeftTrigger = new TriggerButton(operatorController, false, RobotMap.DriverConstants.TRIGGER_DEAD_ZONE);

	public OI(){
		driverRightBumper.whenPressed(new SwitchDriveMode());
		driverRightBumper.whenReleased(new SwitchDriveMode());
		
		operatorYButton.whenPressed(new ClawSpin(RobotMap.ArmConstants.SLOW_OUTPUT_POWER));
		operatorYButton.whenReleased(new ClawIdle());
		operatorAButton.whenPressed(new ClawSpin(RobotMap.ArmConstants.FULL_OUTPUT_POWER));
		operatorAButton.whenReleased(new ClawIdle());
		operatorXButton.whenPressed(new ClawSpin(RobotMap.ArmConstants.FULL_INTAKE_POWER));
		operatorXButton.whenReleased(new ClawIdle());
	}
	
	
	
	public double getDriverLeftXAxis() {
		double leftXControllerValue = driverController.getRawAxis(XBoxControllerMap.LEFT_X_AXIS);
		return checkForDeadzone(leftXControllerValue);

	}
	
	public double getDriverLeftXAxisWODeadzone(){
		double leftXControllerValue = driverController.getRawAxis(XBoxControllerMap.LEFT_X_AXIS);
		return leftXControllerValue;
	}
	public double getDriverLeftYAxis() {
		double leftYControllerValue = driverController.getRawAxis(XBoxControllerMap.LEFT_Y_AXIS);
		return -checkForDeadzone(leftYControllerValue);
	}
	
	public double getDriverLeftYAxisWODeadzone() {
		double leftYControllerValue = driverController.getRawAxis(XBoxControllerMap.LEFT_Y_AXIS);
		return -leftYControllerValue;
	}
	public double getDriverRightXAxis() {
		double rightXControllerValue = driverController.getRawAxis(XBoxControllerMap.RIGHT_X_AXIS);
		return checkForDeadzone(rightXControllerValue);
	}
	
	public double getDriverRightXAxisWODeadzone() {
		double rightXControllerValue = driverController.getRawAxis(XBoxControllerMap.RIGHT_X_AXIS);
		return rightXControllerValue;
	}
	
	public double getDriverRightYAxis() {
		double rightYControllerValue = driverController.getRawAxis(XBoxControllerMap.RIGHT_Y_AXIS);
		return -checkForDeadzone(rightYControllerValue);
	}
	
	public double getDriverRightYAxisWODeadzone() {
		double rightYControllerValue = driverController.getRawAxis(XBoxControllerMap.RIGHT_Y_AXIS);
		return -rightYControllerValue;
	}
	

	public double getDriverLeftTrigger(double scaleValue){
		double leftTriggerValue = driverController.getRawAxis(XBoxControllerMap.LEFT_TRIGGER_AXIS);
		leftTriggerValue = checkForDeadzone(leftTriggerValue);
		return (-leftTriggerValue * scaleValue);
	}


	public double getDriverRightTrigger(double scaleValue){
		double rightTriggerValue = driverController.getRawAxis(XBoxControllerMap.RIGHT_TRIGGER_AXIS);
		rightTriggerValue = checkForDeadzone(rightTriggerValue);
		return (rightTriggerValue * scaleValue);
	}
	
	public double getOperatorLeftYAxis(double scaleValue){
		double leftYControllerValue = operatorController.getRawAxis(XBoxControllerMap.LEFT_Y_AXIS);
		leftYControllerValue = checkForDeadzone(leftYControllerValue);
		return -(leftYControllerValue * scaleValue);
	}
	public double getOperatorLeftXAxis(double scaleValue){
		double leftXControllerValue = operatorController.getRawAxis(XBoxControllerMap.LEFT_X_AXIS);
		leftXControllerValue = checkForDeadzone(leftXControllerValue);
		return (leftXControllerValue * scaleValue);
	}
	
	public double getOperatorRightYAxis(double scaleValue){
		double rightYControllerValue = operatorController.getRawAxis(XBoxControllerMap.RIGHT_Y_AXIS);
		double deadzonedValue = checkForDeadzone(rightYControllerValue);
		double scaledDeadzonedValue = deadzonedValue*scaleValue;
		return -scaledDeadzonedValue;
	}
	
	
	public double getOperatorRightXAxis(double scaleValue){
		double rightXControllerValue = operatorController.getRawAxis(XBoxControllerMap.RIGHT_X_AXIS);
		return checkForDeadzone(rightXControllerValue * scaleValue);
	}
	
	private double checkForDeadzone(double vector) {
		if (Math.abs(vector) <= RobotMap.DriverConstants.DEAD_ZONE_TOLERANCE) {
			return 0.0;
		} else {
			
			double scaledVector = (vector/ Math.abs(vector))*(Math.abs(vector)- RobotMap.DriverConstants.DEAD_ZONE_TOLERANCE)/
					(1-RobotMap.DriverConstants.DEAD_ZONE_TOLERANCE);
			
			return scaledVector;
			
		}
	}

	public static void main (String [] args){
		OI myOI = new OI();
	double returnValue = myOI.checkForDeadzone(0);
		System.out.println(returnValue);
	}
}

