package frc.robot.subsystems.roller;
/** Intake 与 Shooter 共用的单滚轮硬件契约。每个实例持有各自的状态。 */
public interface RollerIO {
  class Inputs { public double output, motorRps; }
  void setOutput(double output);
  void updateInputs(Inputs inputs);
}
