1. Вывести список всех машин и все привязанные к ним детали.
SELECT c.car_name, t.transmission_name, e.engine_name, cb.carbody_name FROM car AS c
    INNER JOIN transmission AS t ON c.transmission_name = t.transmission_name
    INNER JOIN engine AS e ON c.engine_name = e.engine_name
    INNER JOIN carbody AS cb ON c.carbody_name = cb.carbody_name;

2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.

2.1. Вывести отдельно трансмиссии, которые не используются в машине ВАЗ 2106
SELECT t.transmission_name FROM transmission t LEFT JOIN car c ON t.transmission_name = c.transmission_name
    WHERE NOT c.car_name = 'VAZ-2106';

2.2. Вывести отдельно двигатели, которые не используются в машине BMW
SELECT e.engine_name FROM car c RIGHT JOIN engine e ON c.engine_name = e.engine_name WHERE NOT c.car_name = 'BMW';

2.3. Вывести отдельно кузова, которые не используются в AUDI
SELECT cb.carbody_name FROM car c RIGHT JOIN carbody cb ON c.carbody_name = cb.carbody_name
    WHERE NOT c.car_name = 'AUDI' AND NOT cb.carbody_name = 'Универсал';

