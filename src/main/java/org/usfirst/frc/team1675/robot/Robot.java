
package org.usfirst.frc.team1675.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team1675.robot.subsystems.DriveBase;
import org.usfirst.frc.team1675.robot.subsystems.Vision;
import org.usfirst.frc.team1675.robot.subsystems.ClawSubSystem;
import org.usfirst.frc.team1675.robot.commands.drivebase.TurnWithGyro;
import org.usfirst.frc.team1675.robot.subsystems.ClawArm;
import org.usfirst.frc.team1675.robot.subsystems.ClawSubSystem;
import org.usfirst.frc.team1675.robot.commands.auto.FrenchRampsAuto;
import org.usfirst.frc.team1675.robot.commands.auto.LowBarScore;
import org.usfirst.frc.team1675.robot.commands.auto.LowBarSecondRobot;
import org.usfirst.frc.team1675.robot.commands.auto.PortcullisAuto;
import org.usfirst.frc.team1675.robot.commands.auto.RockWallAuto;
import org.usfirst.frc.team1675.robot.commands.auto.RoughTerrainAuto;
import org.usfirst.frc.team1675.robot.commands.claw.ClawIntakeForTime;
import org.usfirst.frc.team1675.robot.commands.claw.ClawOutputForTime;
import org.usfirst.frc.team1675.robot.commands.clawarm.ClawArmMoveForXSeconds;
import org.usfirst.frc.team1675.robot.commands.drivebase.DriveForDistance;
import org.usfirst.frc.team1675.robot.commands.drivebase.DriveStraightForTime;
import org.usfirst.frc.team1675.robot.subsystems.DriveBase;
import org.usfirst.frc.team1675.robot.subsystems.LiftArm;
import org.usfirst.frc.team1675.robot.utils.AutoChooser;
import org.usfirst.frc.team1675.robot.utils.Zamboni;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static DriveBase driveBase;
	public static ClawSubSystem clawSub;
	public static ClawArm clawArm;
	public static LiftArm liftArm;
	public static Vision vision;

	static{
		try{
			driveBase = new DriveBase();
			clawSub = new ClawSubSystem(false); //false everywhere
			clawArm = new ClawArm(false); // true for practice robot, false for competition robot
			liftArm = new LiftArm();
			vision = new Vision();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
		
	public static OI oi;

    Command autonomousCommand;
    SendableChooser chooser;   
    AutoChooser autoChooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();	
		autoChooser = new AutoChooser();
//        chooser = new SendableChooser();
//        chooser.addObject("Low Bar Score", new LowBarScore());
//        chooser.addObject("Just Drop Claw", new ClawArmMoveForXSeconds(0.5, -0.3));
//        chooser.addObject("Spit", new ClawOutputForTime(2.0));
//        chooser.addObject("Suck", new ClawIntakeForTime(1.0));
//        chooser.addObject("Timeout Test", new DriveForDistance(300.0, 1.0));
//        chooser.addObject("Port", new PortcullisAuto());
//        chooser.addObject("French", new FrenchRampsAuto());
//        chooser.addObject("Rough", new RoughTerrainAuto());
//        SmartDashboard.putData("Auto mode", chooser);
        SmartDashboard.putString("george", "banana");
//        chooser.addObject("My Auto", new MyAutoCommand());
       
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
    	Zamboni.clear();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
	  public void autonomousInit() {
		  autonomousCommand = autoChooser.generateAuto();
//        autonomousCommand = (Command) chooser.getSelected();
    	//autonomousCommand = new TurnWithGyro(90.0);
//		autonomousCommand = new DriveForDistance(219.0);
//    	autonomousCommand = new LowBarSecondRobot();
		  //autonomousCommand = new TurnWithGyro(90.0);
		  //autonomousCommand = new DriveStraightForTime(0.5, 3.0);
		  //autonomousCommand = new ClawArmMoveForXSeconds(0.5, -0.3);
		 // autonomousCommand = new DriveFOrDistanceAndReportDisplacement(60.0);
		  //autonomousCommand = new LowBarScore();
		  //autonomousCommand = new FrenchRampsAuto();
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	

    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
