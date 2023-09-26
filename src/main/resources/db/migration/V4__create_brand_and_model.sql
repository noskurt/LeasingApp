CREATE TABLE "brand"
(
  "id"   UUID PRIMARY KEY,
  "name" TEXT NOT NULL
);

CREATE TABLE "model"
(
  "id"       UUID PRIMARY KEY,
  "name"     TEXT NOT NULL,
  "brand_id" UUID NOT NULL
);

ALTER TABLE "model"
  ADD CONSTRAINT "fk_model_brand_id"
    FOREIGN KEY ("brand_id") REFERENCES "brand" ("id");
