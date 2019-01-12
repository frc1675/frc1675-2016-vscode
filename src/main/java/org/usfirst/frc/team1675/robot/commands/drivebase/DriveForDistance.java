package org.usfirst.frc.team1675.robot.commands.drivebase;

import java.security.spec.DSAGenParameterSpec;

import org.usfirst.frc.team1675.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveForDistance extends PIDCommand {

	
	private static final double kP = 0.00018;
	private static final double kI = 0;
	private static final double kD = 0.00065;
	
	//720 tick/rotation / 18.85 in/rotation = 38.2 ticks/inch
	private static final double TICKS_PER_INCH = 38.2;
	
	private static final double TOLERANCE = TICKS_PER_INCH * 2.0;
	
	

	
	private double inchesSetpoint;
	private int initialEncoderValue;
	
    public DriveForDistance(double inchesSetpoint) {
    	super(kP, kI, kD);
        requires(Robot.driveBase);
        this.inchesSetpoint = inchesSetpoint;

        this.getPIDController().setOutputRange(-1.0, 1.0);
        this.getPIDController().setAbsoluteTolerance(TOLERANCE);
        this.getPIDController().setToleranceBuffer(20);
    }
    
    public DriveForDistance(double inchesSetpoint, double timeout) {
    	this(inchesSetpoint);
    	this.setTimeout(timeout);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    //	Robot.driveBase.setTalonsToVoltageMode();
    	initialEncoderValue = Robot.driveBase.getEncPosition();
    	this.getPIDController().setSetpoint(initialEncoderValue + (inchesSetpoint * TICKS_PER_INCH));

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//SmartDashboard.putNumber("DFD Setpoint", this.getSetpoint());
    	//SmartDashboard.putNumber("DFD Position", this.getPosition());
    	//SmartDashboard.putNumber("DFD Error", this.getPIDController().getError());
    	//SmartDashboard.putNumber("DFD Average Error", this.getPIDController().getAvgError());
    	SmartDashboard.putBoolean("DFD On Target", this.getPIDController().onTarget());
    	
    	
    	return this.isTimedOut() || this.getPIDController().onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	this.getPIDController().disable();
    	Robot.driveBase.setLeftMotorPower(0.0);
		Robot.driveBase.setRightMotorPower(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	@Override
	protected double returnPIDInput() {
		int encPos = Robot.driveBase.getEncPosition();
		//SmartDashboard.putNumber("DS Enc Pos", encPos - initialEncoderValue);
		//SmartDashboard.putNumber("DS Inches", (encPos - initialEncoderValue) / TICKS_PER_INCH);
		return encPos;
	}

	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("DS PIDOut", output);
		boolean inTheZone = Math.abs(this.getPIDController().getError()) < TOLERANCE;
		if (inTheZone){
			output = 0.0;
		}
		if (!inTheZone && Math.abs(output) < .1 && output != 0.0){
			output = output/Math.abs(output) * .1;
		}
		
		Robot.driveBase.setLeftMotorPower(output);
		Robot.driveBase.setRightMotorPower(output);
		
	}
}
