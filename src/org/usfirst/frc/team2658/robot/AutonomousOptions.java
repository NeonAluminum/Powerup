package org.usfirst.frc.team2658.robot;

import edu.wpi.first.wpilibj.Timer;
//TODO: Implement Timers(?), Implement Autonomous Methods, Calaculate distances for autonomous
public class AutonomousOptions {
    Timer timer = new Timer();

    private static void moveForward(double distance, double power){
        double drivePower = 0;
        while(Robot.distanceTraveled < distance){
            drivePower = power * ((distance - Robot.distanceTraveled)/distance);
            Robot.driveTrain.arcadeDrive(power, 0);

        }
        Robot.distanceTraveled = 0;
        resetEncoders();
    }
    //  negative angle to turn left, positive to turn right. Speed to turn at, tolerance of turn (how off can it be)
    private static void turn(double angle, double power, int tolerance){
        double turnPower = power * (angle > 0 ? 1:-1);
        double targetAngle = Robot.gyro.getAngle() + angle;

        while(Math.abs(targetAngle - Robot.gyro.getAngle()) > tolerance){
            Robot.driveTrain.arcadeDrive(0, turnPower);
        }


    }
    private static void resetEncoders(){
        Robot.rEncoder.reset();
        Robot.lEncoder.reset();
    }

    public static void straightDiagL(){
            // go straight, turn towards scale (left), go to scale
    }
    public static void straightDiagR(){
            // go straight, turn towards scale (right), go to scale
    }
    public static void straight(){
            // go straight to scale (side-independent)
    }
    public static void crossLine(){
            // basic cross auto line
    }
    public static void nintendoSwitch(char joycon){
        if(joycon == 'r'){
            // turn towards switch,
            // go to switch (right)
        }
        else if (joycon == 'l'){
            // turn towards switch,
            // go to switch(left)
        }
    }

}
