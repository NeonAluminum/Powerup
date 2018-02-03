package org.usfirst.frc.team2658.robot;

import edu.wpi.first.wpilibj.Timer;

public class AutonomousOptions {
    Timer timer = new Timer();
    public void moveForward(double distance, double power){
        while(Robot.distanceTraveled < distance){
            Robot.driveTrain.arcadeDrive(power, 0); // will implement slow down to stop

        }
    }
    public void turn(double angle, double power){
        // gyro?, time?, wat
    }

}
