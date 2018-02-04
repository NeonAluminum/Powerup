package org.usfirst.frc.team2658.robot;

/* AUTHOR: Neal Chokshi */
import edu.wpi.first.wpilibj.Timer;
//TODO: Implement Timers(?), Implement Autonomous Methods, Calaculate distances for autonomous
public class AutonomousOptions {
    Timer timer = new Timer();

    private static void moveForward(double distance, double power){
        double drivePower = 0;

        // while not there yet...
        while(Robot.distanceTraveled < distance){
            // decrement drive power until it is there to slow down to a stop
            drivePower = power * ((distance - Robot.distanceTraveled)/distance);
            // drive there yay
            Robot.driveTrain.arcadeDrive(power, 0);

        }
        // reset encoder related values
        Robot.distanceTraveled = 0;
        resetEncoders();
    }

    //  negative angle to turn left, positive to turn right. Speed to turn at, tolerance of turn (how off can it be)
    private static void turn(double angle, double power, int tolerance){
        // make turn power positive or negative based on which we to turn
        double turnPower = power * (angle > 0 ? 1:-1);
        // target angle is where robot is now plus angle param. this is to bypass using the
        // janky gyro.reset() method
        double targetAngle = Robot.gyro.getAngle() + angle;

        // while the target angle is not close enough...
        while(Math.abs(targetAngle - Robot.gyro.getAngle()) > tolerance){
            // turn
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
    /* AUTHOR: Neal Chokshi */
}
