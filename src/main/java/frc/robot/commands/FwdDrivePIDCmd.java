// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSub;

public class FwdDrivePIDCmd extends CommandBase {
  /** Creates a new ForwardDrivePID. */
  private final DriveSub driveSub; 
  
  private double forwardValue;

  private double setPoint; // Target distance
  private double error;
  private final double kP = 0.011625 * 1.80; // That times 32 should give 0.5 speed. 

  private double outputSpeed; // This is the speed to run the motors. 

  public FwdDrivePIDCmd(DriveSub driveSubsystem, double targetPoint) {
    driveSub = driveSubsystem;
    setPoint = targetPoint;
    addRequirements(driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // driveSub.resetEncoders();
    System.out.print("Current Distance: " + (driveSub.getForwardDistance()));
    System.out.print("Target Distance: " + setPoint);
    // setPoint += driveSub.getForwardDistance();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    forwardValue = driveSub.getForwardDistance();
    error = setPoint - forwardValue; // Negative - negative is positive
    outputSpeed = error * kP;  // Negative
    System.out.print(outputSpeed);
    driveSub.driveTank(outputSpeed, outputSpeed); //Move both at the output speed - drive forward at said speed. 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveSub.resetEncoders(); // Don't know why I have to do it twice
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Math.abs(setPoint - driveSub.getForwardDistance()) < 7){
      return true;
    }else{
      return false;
    }
  }
}
