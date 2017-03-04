DELETE FROM student;
DELETE FROM person;
DELETE FROM record_book;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO person (passport_seria, passport_number, last_name, first_name, middle_name) VALUES
  (0501, 100000, 'Иванов', 'Артем', 'Владимирович'),
  (0511, 100001, 'Сидоров', 'Алексей', 'Петрович'),
  (0502, 200000, 'Петров', 'Сергей', 'Николаевич'),
  (0512, 200001, 'Кузлякин', 'Алексей', 'Сергеевич'),
  (0503, 300000, 'Быков', 'Дмитрий', 'Васильевич'),
  (0513, 300001, 'Шувалов', 'Владимир', 'Владимирович'),
  (0504, 400000, 'Артемьев', 'Иван', 'Михайлович'),
  (0514, 400001, 'Богачев', 'Михаил', 'Сергеевич'),
  (0505, 500000, 'Епураш', 'Сергей', 'Владимирович'),
  (0515, 500001, 'Козлов', 'Николай', 'Иванович');

INSERT INTO record_book (code) VALUES
  (1000001),
  (2000002),
  (3000003),
  (4000004),
  (5000005),
  (6000006),
  (7000007);

INSERT INTO student (record_book, person, group_name) VALUES
  (100010, 100000, '501'),
  (100011, 100001, '501'),
  (100012, 100002, '501'),
  (100013, 100003, '888'),
  (100014, 100004, '888'),
  (100015, 100005, '121'),
  (100016, 100006, '121'),
  (null, 100007, '737'),
  (null, 100008, '737'),
  (null, 100009, '737');