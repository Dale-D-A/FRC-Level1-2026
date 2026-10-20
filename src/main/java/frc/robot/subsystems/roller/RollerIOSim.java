package frc.robot.subsystems.roller;
import edu.wpi.first.wpilibj.DriverStation;
/** 无硬件的线性教学模型，不能用来整定实车 PID。 */
public class RollerIOSim implements RollerIO {
  private double output;
  @Override public void setOutput(double output) { this.output=output; }
  @Override public void updateInputs(Inputs inputs) {
    if (!DriverStation.isEnabled()) output=0;
    inputs.output=output; inputs.motorRps=output*80;
  }
}
