1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT prod_name, type_id FROM products WHERE type_id = (SELECT type_id FROM type WHERE type_name = 'СЫР');

2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
SELECT prod_name FROM products WHERE prod_name LIKE '%мороженое';

3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT prod_name, expired_date FROM products WHERE expired_date > '2020-06-30' AND expired_date < '2020-08-25';

4. Написать запрос, который выводит самый дорогой продукт.
SELECT prod_name, price FROM products WHERE price = (SELECT MAX(price) FROM products);

5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT SUM(count) FROM products WHERE type_id = (SELECT type_id FROM type WHERE type_name = 'МОЛОКО');

6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО".
 SELECT prod_name FROM products WHERE type_id IN (
    (SELECT type_id FROM type WHERE type_name = 'СЫР'),
    (SELECT type_id FROM type WHERE type_name = 'МОЛОКО')
);

7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT type_name FROM type WHERE type_id =
    (SELECT type_id FROM products GROUP BY type_id HAVING SUM(count) < 10);

8. Вывести все продукты и их тип.
SELECT t.type_name, p.prod_name FROM type as t INNER JOIN products as p ON t.type_id = p.type_id;
