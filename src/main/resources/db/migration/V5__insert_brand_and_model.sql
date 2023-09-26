INSERT INTO "brand" ("id", "name")
VALUES (gen_random_uuid(), 'BMW'),
       (gen_random_uuid(), 'Mercedes'),
       (gen_random_uuid(), 'Audi'),
       (gen_random_uuid(), 'Dacia'),
       (gen_random_uuid(), 'Fiat');

INSERT INTO "model" ("id", "name", "brand_id")
VALUES (gen_random_uuid(), 'M2', (SELECT "id" FROM "brand" WHERE "name" = 'BMW')),
       (gen_random_uuid(), 'M3', (SELECT "id" FROM "brand" WHERE "name" = 'BMW')),
       (gen_random_uuid(), 'X1', (SELECT "id" FROM "brand" WHERE "name" = 'BMW')),
       (gen_random_uuid(), 'A220', (SELECT "id" FROM "brand" WHERE "name" = 'Mercedes')),
       (gen_random_uuid(), 'AMG 45S', (SELECT "id" FROM "brand" WHERE "name" = 'Mercedes')),
       (gen_random_uuid(), 'CLK200', (SELECT "id" FROM "brand" WHERE "name" = 'Mercedes')),
       (gen_random_uuid(), 'A1', (SELECT "id" FROM "brand" WHERE "name" = 'Audi')),
       (gen_random_uuid(), 'A3', (SELECT "id" FROM "brand" WHERE "name" = 'Audi')),
       (gen_random_uuid(), 'RS3', (SELECT "id" FROM "brand" WHERE "name" = 'Audi')),
       (gen_random_uuid(), 'Duster', (SELECT "id" FROM "brand" WHERE "name" = 'Dacia')),
       (gen_random_uuid(), 'Sandero', (SELECT "id" FROM "brand" WHERE "name" = 'Dacia')),
       (gen_random_uuid(), 'Spring', (SELECT "id" FROM "brand" WHERE "name" = 'Dacia')),
       (gen_random_uuid(), '500', (SELECT "id" FROM "brand" WHERE "name" = 'Fiat')),
       (gen_random_uuid(), 'Linea', (SELECT "id" FROM "brand" WHERE "name" = 'Fiat')),
       (gen_random_uuid(), 'Panda', (SELECT "id" FROM "brand" WHERE "name" = 'Fiat'));
