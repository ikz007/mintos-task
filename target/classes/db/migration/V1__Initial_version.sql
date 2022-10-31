CREATE TABLE IF NOT EXISTS geolocation (
    id INT PRIMARY KEY,
    ip_address VARCHAR(45) NOT NULL,
    latitude DECIMAL NOT NULL,
    longitude DECIMAL NOT NULL
);

CREATE TABLE IF NOT EXISTS weather (
    id INT PRIMARY KEY,
    details JSON,
    request_time timestamp WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    location_id INT,
    FOREIGN KEY (location_id) REFERENCES geolocation(id)
);