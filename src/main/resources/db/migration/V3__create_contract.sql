CREATE TABLE "contract"
(
  "id"              UUID PRIMARY KEY,
  "contract_number" NUMERIC NOT NULL,
  "monthly_rate"    NUMERIC NOT NULL,
  "vehicle_id"      UUID    NOT NULL,
  "customer_id"     UUID    NOT NULL
);

ALTER TABLE "contract"
  ADD CONSTRAINT "fk_contract_vehicle_id"
    FOREIGN KEY ("vehicle_id") REFERENCES "vehicle" ("id"),
  ADD CONSTRAINT "fk_contract_customer_id"
    FOREIGN KEY ("customer_id") REFERENCES "customer" ("id"),
  ADD CONSTRAINT "uq_vehicle_id" UNIQUE ("vehicle_id");
