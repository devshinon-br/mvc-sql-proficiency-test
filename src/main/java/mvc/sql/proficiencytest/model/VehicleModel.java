package mvc.sql.proficiencytest.model;

public enum VehicleModel {
    HATCH("hatch"),
    SEDAN("sedan"),
    PICKUP("pick-up"),
    SUV("suv");

    public final String model;

    VehicleModel(final String model) {
        this.model = model;
    }
}
