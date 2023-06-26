ALTER SEQUENCE hibernate_sequence RESTART WITH 10000;

-- VENUE
INSERT INTO `venue`
    (venue_id, name, capacity, possible_times, type)
VALUES
    (100001, '공연장A', 1000, '00:00~00:00', 'FIXED_SEAT')
;

-- SEATS
INSERT INTO `seat`
    (seat_id, `number`, seat_type, venue_id)
VALUES
    (100001,  'A1',    'PREMIUM', 100001)
  , (100002,  'A2',    'PREMIUM', 100001)
  , (100003,  'A3',    'PREMIUM', 100001)
  , (100004,  'A4',    'PREMIUM', 100001)
  , (100005,  'A5',    'PREMIUM', 100001)
  , (100006,  'A6',    'PREMIUM', 100001)
  , (100007,  'A7',    'PREMIUM', 100001)
  , (100008,  'A8',    'PREMIUM', 100001)
  , (100009,  'A9',    'PREMIUM', 100001)
  , (100010, 'A10',    'PREMIUM', 100001)
  , (100101,  'B1',        'VIP', 100001)
  , (100102,  'B2',        'VIP', 100001)
  , (100103,  'B3',        'VIP', 100001)
  , (100104,  'B4',        'VIP', 100001)
  , (100105,  'B5',        'VIP', 100001)
  , (100106,  'B6',        'VIP', 100001)
  , (100107,  'B7',        'VIP', 100001)
  , (100108,  'B8',        'VIP', 100001)
  , (100109,  'B9',        'VIP', 100001)
  , (100110, 'B10',        'VIP', 100001)
  , (110001,  'C1',    'REGULAR', 100001)
  , (110002,  'C2',    'REGULAR', 100001)
  , (110003,  'C3',    'REGULAR', 100001)
  , (110004,  'C4',    'REGULAR', 100001)
  , (110005,  'C5',    'REGULAR', 100001)
  , (110006,  'C6',    'REGULAR', 100001)
  , (110007,  'C7',    'REGULAR', 100001)
  , (110008,  'C8',    'REGULAR', 100001)
  , (110009,  'C9',    'REGULAR', 100001)
  , (110010, 'C10',    'REGULAR', 100001)
;

-- PERFORMANCE
INSERT INTO `performance`
    (performance_id, venue_id, name, description, genre, `capacity`, `date`, `start_time`, `end_time`)
VALUES
    (100001, 100001, '공연A', '공연이에요', 'CONCERT', 30, '2023-06-30', '14:00', '17:00')
;