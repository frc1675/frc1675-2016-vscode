package org.usfirst.frc.team1675.robot.utils;

import org.usfirst.frc.team1675.robot.XBoxControllerMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class TriggerButton extends Button {

	Joystick stick;
	boolean isLeft;
	double deadZone;

	public TriggerButton(Joystick joystick, boolean isLeft, double deadZone) {
		this.isLeft = isLeft;
		stick = joystick;
		this.deadZone = deadZone;
	}

	public boolean get() {
		if (isLeft) {
			System.out
					.println(Math.abs(stick.getRawAxis(XBoxControllerMap.LEFT_TRIGGER_AXIS)) > deadZone);
			return Math.abs(stick.getRawAxis(XBoxControllerMap.LEFT_TRIGGER_AXIS)) > deadZone;
		} else {
			System.out.println(Math.abs(stick.getRawAxis(XBoxControllerMap.RIGHT_TRIGGER_AXIS)) > deadZone);
			return Math.abs(stick.getRawAxis(XBoxControllerMap.RIGHT_TRIGGER_AXIS)) > deadZone;
		}

	}

}
