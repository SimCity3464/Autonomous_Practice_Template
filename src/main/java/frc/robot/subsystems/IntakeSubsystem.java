// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */
  private final Spark 
    intakeBottom= new Spark(0), 
    intakeTop = new Spark(1); 

  public IntakeSubsystem() {}

  //turn on both bottom and top intake
  public void intakeBoth() {
      intakeBottom.set(-.75);
      intakeTop.set(-.65);
    
  }
  
  public void intakeBottom(double speed) {
    // Turn on motor for intake bottom
      intakeBottom.set(speed);    
  }
  public void intakeTop(double speed) {
    // Turn on motor for intake bottom
      intakeTop.set(speed);    
  }


  public void stopIntakes() {
    // Stops all motors
    intakeBottom.set(0);
    intakeTop.set(0);
  }

  public void reverse(){
    //reverses both intakes
    intakeBottom.set(.75);
    intakeTop.set(.65);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
