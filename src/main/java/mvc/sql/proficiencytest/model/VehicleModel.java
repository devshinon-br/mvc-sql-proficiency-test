package mvc.sql.proficiencytest.model;

public enum VehicleModel {
    HATCH("hatch"),
    SEDAN("sedan"),
    PICKUP("pick-up"),
    SUV("suv");

    private String description;

    VehicleModel(final String model) {
        this.description = model;
    }

    public String getDescription() {
        return description;
    }

    public static VehicleModel getVehicleModel(final String description) {
        if (description != null) {
            for(VehicleModel vehicleModel : VehicleModel.values()) {
                if(vehicleModel.description.equals(description)) {
                    return vehicleModel;
                }
            }
        }

        return null;
    }
}
