// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCmd extends CommandBase {
  /** Creates a new IntakeCmd. */
  private final IntakeSubsystem intakeSub;
  private boolean forward; //Sets the direction of the intake

  public IntakeCmd(IntakeSubsystem intakeSubsystem, boolean goFoward) {
    intakeSub = intakeSubsystem;
    forward = goFoward;
    addRequirements(intakeSub); // If another command uses this, 
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(forward){
      intakeSub.intakeBottom(-0.75); // If it's forward, run the motor so we can intake the ball
    }else{
      intakeSub.intakeBottom(0.75); //Eject the bottom. 
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intakeSub.intakeBottom(0.00);  // Stop intake when we don't use it. 
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
