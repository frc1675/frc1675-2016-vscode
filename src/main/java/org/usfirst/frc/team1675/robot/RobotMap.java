package org.usfirst.frc.team1675.robot;

public class RobotMap {

	public static class PWMChannels {
		public static final int LIFTER_MOTOR = 0;
		public static final int RIGHT_BACK_MOTOR = 1;
		public static final int RIGHT_FRONT_MOTOR = 2;
		public static final int LEFT_FRONT_MOTOR = 3;
		public static final int CLAW_TOP_MOTOR = 7;
		public static final int CLAW_BOTTOM_MOTOR = 8;
		public static final int LEFT_BACK_MOTOR = 9;

	}
	public static class CANDeviceIDs{
		public static final int CLAW_ARM_MOTOR = 1;
		public static final int LEFT_MOTOR = 3;
		public static final int RIGHT_MOTOR = 2;
		
	}

	public static class PDChannels {
		public static final int EMPTY_PORT_ZERO = 0;
		public static final int EMPTY_PORT_ONE = 1;
		public static final int EMPTY_PORT_TWO = 2;
		public static final int EMPTY_PORT_THREE = 3;
	}

	public static class DIOChannels {
		public static final int ARM_DOWN_LIMIT_SWITCH = 7;
		public static final int ARM_UP_LIMIT_SWITCH = 6;
		public static final int LIFTER_DOWN_LIMIT_SWITCH = 8;
		public static final int LIFTER_UP_LIMIT_SWITCH = 9;

	}

	public static class ArmConstants {
		public static final double HOME_POSITION = 0;
		public static final double SCORE_POSITION = 1;
		public static final int MINIMUM = 90;
		public static final int MAXIMUM = 200;
		public static final double SCALING_VALUE = 0.75;

		public static final double P = 0.01;
		public static final double I = 0;
		public static final double D = 0;
		
		public static final double SLOW_OUTPUT_POWER = .2;
		public static final double FULL_OUTPUT_POWER = 1.0;
		public static final double FULL_INTAKE_POWER = -1.0;

	}

	public static class DriverConstants {
		public static final double DEAD_ZONE_TOLERANCE = .1675;
		public static final double TRIGGER_DEAD_ZONE = .5;
	}
	
	public static class LiftArmConstants{
		public static final double LIFT_ARM_UP_SPEED = 1;
		public static final double LIFT_ARM_DOWN_SPEED = -1;
	}
	public static class DriveBaseConstants{
		public static final double DRIVE_BASE_MOTOR_DEAD_ZONE = .1675;
	}

}
