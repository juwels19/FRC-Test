package org.usfirst.frc.team6513.robot.subsystems;

import org.usfirst.frc.team6513.robot.RobotMap;
import org.usfirst.frc.team6513.robot.commands.DriveStop;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveSubsystem extends Subsystem {
	
	Spark frontLeft = new Spark(RobotMap.FRONT_LEFT_DRIVE_PORT);
	Spark rearLeft = new Spark(RobotMap.REAR_LEFT_DRIVE_PORT);
	Spark frontRight = new Spark(RobotMap.FRONT_RIGHT_DRIVE_PORT);
	Spark rearRight = new Spark(RobotMap.REAR_RIGHT_DRIVE_PORT);
	
	Encoder left_drive = new Encoder(RobotMap.ENCODER_PORT_3, RobotMap.ENCODER_PORT_4, false, Encoder.EncodingType.k4X);
	Encoder right_drive = new Encoder(RobotMap.ENCODER_PORT_5, RobotMap.ENCODER_PORT_6, false, Encoder.EncodingType.k4X);
	ADXRS450_Gyro gyro = new ADXRS450_Gyro(Port.kOnboardCS0);
	
	SpeedControllerGroup left = new SpeedControllerGroup(frontLeft, rearLeft);
	SpeedControllerGroup right = new SpeedControllerGroup(frontRight, rearRight);
	
	DifferentialDrive drive = new DifferentialDrive(left, right);
	
	
	public DriveSubsystem() {
		// set motor inversions if needed
		reset();
	}
	
    
    public void driveTeleop(double throttle, double yaw) {
    	if (Math.abs(throttle) < RobotMap.DEADBAND) {
    		throttle = 0.0;
    	}
    	if (Math.abs(yaw) < RobotMap.DEADBAND) {
    		yaw = 0.0;
    	}
    	
    	double accel = 0.0;
    	
    	// Acceleration loop
    	while (accel < throttle) {
    		drive.arcadeDrive(accel, yaw);
    		accel += 0.1;
    		if (accel > throttle) {
    			accel = throttle;
    		}
    	}
    	drive.arcadeDrive(accel, yaw);
    }
    
    public void driveSlowerTeleop(double throttle, double yaw, double damper) {
    	if (Math.abs(throttle) < RobotMap.DEADBAND) {
    		throttle = 0.0;
    	}
    	if (Math.abs(yaw) < RobotMap.DEADBAND) {
    		yaw = 0.0;
    	}
    	if (damper < -1 ) {
    		damper = -1;
    	}
    	if (damper > 1) {
    		damper = 1;
    	}
    	drive.arcadeDrive(throttle * damper, yaw * damper);
    }
    
    public void driveAuto(double throttle, double yaw) {
    	drive.arcadeDrive(throttle, yaw);
    }
    
    public void driveStraightAuto(double throttle, double distance) {
    	double currentDistance = getAvgDistance();
    	if (currentDistance < distance) {
    		double error = getLeftEnc() - getRightEnc();
        	double yaw = RobotMap.DRIVE_KP * error;
        	drive.arcadeDrive(throttle, yaw);
    	} else {
    		stop();
    	}
    	
    }
    
    public void rotateToAngle(double target) {
    	double error;
    	double yaw;
    	error = target - getAngle(); // in degrees
    	
    	if (error > RobotMap.AUTO_DRIVE_ANGLE_THRESHOLD) {
    		yaw = RobotMap.ROTATION_KP * error;
    	} else {
    		yaw = 0.0;
    	}
    	drive.arcadeDrive(0.0, yaw);
    }
	
	public double getLeftEnc() {
		return left_drive.getRaw();
	}
	
	public double getRightEnc() {
		return right_drive.getRaw();
	}
	
	public double getAvgDistance() {
		return (((getLeftEnc() - getRightEnc()) / 2) / RobotMap.TICKS_PER_INCH );
	}
	
	public double getAngle() {
		return gyro.getAngle();
	}
	
	public void reset() {
		gyro.reset();
		left_drive.reset();
		right_drive.reset();
	}
    
    public void stop() {
    	drive.arcadeDrive(0.0, 0.0);
    }
    
    @Override
    public void initDefaultCommand() {
    	setDefaultCommand(new DriveStop());
    }
}

