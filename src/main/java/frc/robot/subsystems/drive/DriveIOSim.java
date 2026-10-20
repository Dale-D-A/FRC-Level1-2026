package frc.robot.subsystems.drive;
import edu.wpi.first.wpilibj.DriverStation;
/** 确定性的教学模拟：输出与转速线性对应；没有质量、惯性、轮胎或碰撞模型。 */
public class DriveIOSim implements DriveIO {
  private double left, right;
  @Override public void setOutputs(double left, double right) { this.left=left; this.right=right; }
  @Override public void updateInputs(Inputs inputs) {
    if (!DriverStation.isEnabled()) stop();
    inputs.leftOutput=left; inputs.rightOutput=right;
    inputs.leftMotorRps=left*80; inputs.rightMotorRps=right*80;
  }
}
