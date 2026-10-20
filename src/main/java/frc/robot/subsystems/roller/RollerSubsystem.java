package frc.robot.subsystems.roller;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
/** 一个类，两个实例：Intake 和 Shooter 分别拥有硬件和命令占用权。 */
public class RollerSubsystem extends SubsystemBase {
  private final String label;
  private final RollerIO io;
  private final RollerIO.Inputs inputs=new RollerIO.Inputs();
  public RollerSubsystem(String label, RollerIO io) { this.label=label; this.io=io; setName(label); }
  public void stop() { io.setOutput(0); }
  public Command runAt(double output) {
    // runEnd 自动要求当前 Subsystem；结束或被打断时归零。
    return runEnd(() -> io.setOutput(MathUtil.clamp(output,-0.25,0.25)),this::stop);
  }
  @Override public void periodic() {
    if (!DriverStation.isEnabled()) stop();
    io.updateInputs(inputs);
    SmartDashboard.putNumber(label+"/Output",inputs.output);
    SmartDashboard.putNumber(label+"/MotorRps",inputs.motorRps);
  }
}
