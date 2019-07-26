package org.usfirst.frc.team6513.robot.commands;

import org.usfirst.frc.team6513.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorStop extends Command {

    public ElevatorStop() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevatorSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevatorSubsystem.elevatorStop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevatorSubsystem.elevatorStop();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevatorSubsystem.elevatorStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
