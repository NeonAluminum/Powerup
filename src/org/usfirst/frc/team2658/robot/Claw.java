package org.usfirst.frc.team2658.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//place holder class for claw mechanism -- will implement actual claw class once created
public class Claw {

	/* Author --> Gokul Swaminathan */
	// port numbers
	private static final int grabbyPortOne = 0;
	private static final int grabbyPortTwo = 1;

	private static DoubleSolenoid grabby = new DoubleSolenoid(grabbyPortOne, grabbyPortTwo);

	static public void openClaw() {
		grabby.set(DoubleSolenoid.Value.kForward);
	}

	static public void closeClaw() {
		grabby.set(DoubleSolenoid.Value.kReverse);
	}
	

	static public void clawOff() {
		grabby.set(DoubleSolenoid.Value.kOff);
	}

	//show the status of the claw on the smartdashboard
	static public void clawStatus() {
		if (grabby.get() == DoubleSolenoid.Value.kForward) {
			SmartDashboard.putString("Grabby Arm", "OPEN");
		}
		else if (grabby.get() == DoubleSolenoid.Value.kReverse) {
			SmartDashboard.putString("Grabby Arm", "CLOSE");
		}
		else {
			SmartDashboard.putString("Grabby Arm", "OFF");
		}
	}
	/* Author --> Gokul Swaminathan */
}
