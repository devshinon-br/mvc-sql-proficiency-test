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
    entry_time timestamp NOT NULL,
    departure_time timestamp,
    total_value NUMERIC(10, 2),
    PRIMARY KEY (id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(id)
);
