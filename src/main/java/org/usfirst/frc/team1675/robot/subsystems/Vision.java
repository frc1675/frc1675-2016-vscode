package org.usfirst.frc.team1675.robot.subsystems;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Vision extends Subsystem {
    
	 CameraServer server;
	 
	 public Vision(){
		 
		 server = CameraServer.getInstance();
	     //   server.setQuality(100);	        //the camera name (ex "cam0") can be found through the roborio web interface
	    //    server.startAutomaticCapture("cam0");
	 }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	 //k.ja

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}