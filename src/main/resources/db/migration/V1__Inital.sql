CREATE TABLE IF NOT EXISTS vehicle (
    id UUID NOT NULL,
    license_plate VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    color VARCHAR(100) NOT NULL,
    UNIQUE(license_plate),
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS attendant (
    id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS billing_report (
    id UUID NOT NULL,
    initial_date timestamp NOT NULL,
    final_date timestamp NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS price_list (
    id UUID NOT NULL,
    price_per_hour NUMERIC(10, 2) NOT NULL,
    price_per_additional_hour NUMERIC(10, 2) NOT NULL,
    single_price NUMERIC(10, 2) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ticket (
    id UUID NOT NULL,
    vehicle_id UUID NOT NULL,
    billing_report_id UUID,
    price_list_id UUID,
    entry_time timestamp NOT NULL,
    departure_time timestamp,
    PRIMARY KEY (id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(id),
    FOREIGN KEY (billing_report_id) REFERENCES billing_report(id),
    FOREIGN KEY (price_list_id) REFERENCES price_list(id)
);
