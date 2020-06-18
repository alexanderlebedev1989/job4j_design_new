1. Вывести список всех машин и все привязанные к ним детали.
SELECT c.car_name, t.transmission_name, e.engine_name, cb.carbody_name FROM car AS c
    INNER JOIN transmission AS t ON c.transmission_name = t.transmission_name
    INNER JOIN engine AS e ON c.engine_name = e.engine_name
    INNER JOIN carbody AS cb ON c.carbody_name = cb.carbody_name
    WHERE NOT c.car_name IS NULL;

2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.

SELECT t.transmission_name FROM transmission t LEFT JOIN car c ON t.transmission_name = c.transmission_name
    WHERE c.car_name IS NULL;

SELECT e.engine_name FROM car c RIGHT JOIN engine e ON c.engine_name = e.engine_name WHERE c.car_name IS NULL;

SELECT cb.carbody_name FROM car c RIGHT JOIN carbody cb ON c.carbody_name = cb.carbody_name
    WHERE c.car_name IS NULL;

