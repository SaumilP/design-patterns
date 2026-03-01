package design.pattern.fsm.model;

/**
 * Context pojo object that gets passed between different Actions(transitions)
 */
public class Robot {
    private String name;
    private String makerName;
    private String serialNumber;
    private String version;

    public Robot() {
    }

    public Robot(String name, String makerName, String serialNumber, String version) {
        this.name = name;
        this.makerName = makerName;
        this.serialNumber = serialNumber;
        this.version = version;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "name='" + name + '\'' +
                ", makerName='" + makerName + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMakerName() {
        return makerName;
    }

    public void setMakerName(String makerName) {
        this.makerName = makerName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
