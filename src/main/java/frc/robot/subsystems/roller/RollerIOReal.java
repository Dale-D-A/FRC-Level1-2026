package frc.robot.subsystems.roller;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import frc.robot.Constants;
/** 实车滚轮示例。方向、限流应按实际机构重新核对。 */
public class RollerIOReal implements RollerIO {
  private final TalonFX motor;
  private double request;
  public RollerIOReal(int canId) {
    motor=new TalonFX(canId);
    var config=new TalonFXConfiguration();
    config.CurrentLimits.StatorCurrentLimitEnable=true;
    config.CurrentLimits.StatorCurrentLimit=Constants.STATOR_LIMIT_AMPS;
    var result=motor.getConfigurator().apply(config);
    if (!result.isOK()) throw new IllegalStateException("滚轮配置失败: "+result);
  }
  @Override public void setOutput(double output) { request=output; motor.set(output); }
  @Override public void updateInputs(Inputs inputs) {
    inputs.output=request; inputs.motorRps=motor.getVelocity().getValueAsDouble();
  }
}
