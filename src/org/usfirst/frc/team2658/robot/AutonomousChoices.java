package org.usfirst.frc.team2658.robot;

import edu.wpi.first.wpilibj.Timer;

public class AutonomousChoices {
	final static double ENCODER_PER_FEET = (Math.PI * 6)/12;
	static Timer timer = new Timer();

	public static void driveRobot(String direction, double distance, double startPower) {
		if(direction.equals("forward")) {
			while(Robot.distanceTraveled < distance) {
				Robot.driveTrain.arcadeDrive(startPower, 0);
			}
			
		}
	}
			
}
