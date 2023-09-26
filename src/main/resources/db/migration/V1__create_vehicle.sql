CREATE TABLE "vehicle"
(
  "id"    UUID PRIMARY KEY,
  "brand" TEXT    NOT NULL,
  "model" TEXT    NOT NULL,
  "year"  NUMERIC NOT NULL,
  "vin"   TEXT    NULL,
  "price" NUMERIC NOT NULL
);
