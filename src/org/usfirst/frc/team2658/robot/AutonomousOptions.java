package org.usfirst.frc.team2658.robot;

import edu.wpi.first.wpilibj.Timer;

public class AutonomousOptions {
    Timer timer = new Timer();

    private void moveForward(double distance, double power){
        while(Robot.distanceTraveled < distance){

            Robot.driveTrain.arcadeDrive(power, 0); // will implement slow down to stop

        }
    }
    //  negative angle to turn left, positive to turn right. Speed to turn at, tolerance of turn (how off can it be)
    private void turn(double angle, double power, int tolerance){
        double turnPower = power * (angle > 0 ? 1:-1);
        double targetAngle = Robot.gyro.getAngle() + angle;

        while(Math.abs(targetAngle - Robot.gyro.getAngle()) > tolerance){
            Robot.driveTrain.arcadeDrive(0, turnPower);
        }


    }

    public void straightDiagL(){

    }
    public void straightDiagR(){

    }
    public void straight(){

    }
    public void crossLine(){

    }

}
