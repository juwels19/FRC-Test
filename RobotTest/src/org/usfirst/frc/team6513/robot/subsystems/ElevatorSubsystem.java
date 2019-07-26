package org.usfirst.frc.team6513.robot.subsystems;

import org.usfirst.frc.team6513.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ElevatorSubsystem extends Subsystem {
	// Positive values move elevator up, negative move elevator down ??
	Spark elevatorMotor1 = new Spark(RobotMap.ELEVATOR_MOTOR_1);
	Spark elevatorMotor2  = new Spark(RobotMap.ELEVATOR_MOTOR_2);
	
	Encoder elevatorEnc = new Encoder(RobotMap.ENCODER_PORT_1, RobotMap.ENCODER_PORT_2, false, Encoder.EncodingType.k4X);
	
	String preset = "Bottom";
	double tolerance = 0.25; // inches
	public boolean autoMode = false;
	
	public ElevatorSubsystem() {
		// check if motors need to be inverted
		encReset();
	}
	
	public void moveToTarget(double target, double throttle) { // throttle is the joystick axis value
		while (true) {
			if (autoMode) {
				double error;
				double output; 
				error = target - getPosition();
				output = (RobotMap.ELEVATOR_KP * error) + RobotMap.ELEVAOTR_KF; // kF only needed if constant force springs are not used
				if (output > 1) {
					output = 1;
				}
				if (output < -1) {
					output = -1;
				}
				elevatorMotor1.set(output);
				elevatorMotor2.set(output);
			} else {
				double damper = 0.75; // scale accordingly
				if (throttle > RobotMap.DEADBAND) {
					elevatorMotor1.set(throttle * damper);
					elevatorMotor2.set(throttle * damper);
				} else if (-throttle < RobotMap.DEADBAND) {
					elevatorMotor1.set(-throttle * damper);
					elevatorMotor2.set(-throttle * damper);
				}
			}
		}
	}

	public void elevatorStop() {
		/* will depend if using constant force springs or not
		 if using cf springs, method is useless
		 with no cf springs, calculate motor speed to keep the elevator up */
		elevatorMotor1.set(0.0);
		elevatorMotor2.set(0.0);
	}
	
	public double getPosition() {
		return getRawEnc() / RobotMap.TICKS_PER_INCH;
	}
	
	public double getRawEnc() {
		return elevatorEnc.getRaw();
	}
	
	public void encReset() {
		elevatorEnc.reset();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

