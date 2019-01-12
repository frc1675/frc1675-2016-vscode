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
public class TurnWithGyro extends PIDCommand {

	
	private static final double kP = 0.015;
	private static final double kI = 0;
	private static final double kD = 0.055;
	
	private static final double TOLERANCE = 1.5;
	
	double degreesSetpoint;
	double initialDegrees;
	
    public TurnWithGyro(double degreesSetpoint) {
    	super(kP, kI, kD);
        requires(Robot.driveBase);
        
        this.degreesSetpoint = degreesSetpoint;
        //this.getPIDController().setInputRange(0.0, 360.0);
        this.getPIDController().setOutputRange(-1.0, 1.0);
        this.getPIDController().setAbsoluteTolerance(TOLERANCE);
        this.getPIDController().setToleranceBuffer(20);
        this.getPIDController().setContinuous(true);
        
    	
    }
    
    public TurnWithGyro(double degreesSetpoint, double timeout) {
    	this(degreesSetpoint);
    	this.setTimeout(timeout);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.driveBase.setTalonsToVoltageMode();
    	//Robot.driveBase.resetGyro();
    	initialDegrees = Robot.driveBase.getAngle();
    	this.getPIDController().setSetpoint((initialDegrees + degreesSetpoint));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	SmartDashboard.putNumber("Gyro Turn Setpoint", this.getSetpoint());
    	SmartDashboard.putNumber("Gyro Turn Position", this.getPosition());
    	//SmartDashboard.putNumber("Gyro Turn Error", this.getPIDController().getError());
    	//SmartDashboard.putNumber("Gyro Turn Average Error", this.getPIDController().getAvgError());
    	SmartDashboard.putBoolean("Gyro On Target", getPIDController().onTarget());
    	
    	
    	return isTimedOut() || getPIDController().onTarget();
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
		return Robot.driveBase.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		boolean inTheZone = Math.abs(this.getPIDController().getError()) < TOLERANCE;
		if (inTheZone){
			output = 0.0;
		}
		if (!inTheZone && Math.abs(output) < .1 && output != 0.0){
			output = output/Math.abs(output) * .1;
		}
		Robot.driveBase.setLeftMotorPower(output);
		Robot.driveBase.setRightMotorPower(-output);
		SmartDashboard.putNumber("Gyro Turn PIDOut", output);
		//SmartDashboard.putNumber("Gyro Angle", Robot.driveBase.getAngle());
		
		
	}
}
