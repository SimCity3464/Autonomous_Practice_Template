// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootCmd extends CommandBase {
  /** Creates a new ShootCmd. */
  private final IntakeSubsystem intakeSub;
  private final ShooterSubsystem shooterSub;
  private Timer shootTimer = new Timer(); // Create a timer object.; //Tracks down the time to run the motors sequencially. 
  private double shootSpeed;
  // private boolean shootEject;


  public ShootCmd(IntakeSubsystem intakeSubsystem, ShooterSubsystem shooterSubsystem, double speed) {
    intakeSub = intakeSubsystem;
    shooterSub = shooterSubsystem;
    shootSpeed = speed;
    addRequirements(intakeSub, shooterSub);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shootTimer.reset(); // Why it's here and not when the command ends? IDK it's just not working. 
    shootTimer.start(); //Start the timer
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("Shoot Timer", shootTimer.get());
    // Run Shooter
    shooterSub.runShooter(shootSpeed); //Star the shooter. 
    Timer.delay(0.65);
    intakeSub.intakeTop(-0.8); // After 0.65 seconds - move the top intake. 
    Timer.delay(0.20);
      intakeSub.intakeBottom(-0.65); //After 0.85 seconds - move the lower intake. 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooterSub.runShooter(0);
    intakeSub.stopIntakes();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(shootTimer.get() > 2.00){
      return true;
    }else{
      return false;
    }
  }
}
