-- ALTER SEQUENCE hibernate_sequence RESTART WITH 10000;

-- USER
INSERT INTO `users`
    (user_id, dtype, login_id, `password`, `name`, email, `status`, `last_login_date`, `last_password_modify_date`)
VALUES
    (100001, 'USER', 'USER1', '$2a$10$uRIUp3k3T3u2bD3RH8RzEuD/rAeZ8zBZVThk6bIsc4Wq/F3c56Kzy', '유저1', 'test@test.com', 'ACTIVE', '2023-06-03', '2023-06-03')
  , (100002, 'USER', 'USER2', '$2a$10$uRIUp3k3T3u2bD3RH8RzEuD/rAeZ8zBZVThk6bIsc4Wq/F3c56Kzy', '유저2', 'test@test.com', 'ACTIVE', '2023-06-03', '2023-06-03')
  , (100003, 'USER', 'USER3', '$2a$10$uRIUp3k3T3u2bD3RH8RzEuD/rAeZ8zBZVThk6bIsc4Wq/F3c56Kzy', '유저3', 'test@test.com', 'ACTIVE', '2023-06-03', '2023-06-03')
  , (100004, 'USER', 'USER4', '$2a$10$uRIUp3k3T3u2bD3RH8RzEuD/rAeZ8zBZVThk6bIsc4Wq/F3c56Kzy', '유저4', 'test@test.com', 'ACTIVE', '2023-06-03', '2023-06-03')
  , (100005, 'USER', 'USER5', '$2a$10$uRIUp3k3T3u2bD3RH8RzEuD/rAeZ8zBZVThk6bIsc4Wq/F3c56Kzy', '유저5', 'test@test.com', 'ACTIVE', '2023-06-03', '2023-06-03')
;
-- BUSINESS
INSERT INTO `users`
    (user_id, dtype, login_id, `password`, `name`, email, `status`, `last_login_date`, `last_password_modify_date`)
VALUES
    (200001, 'BUSINESS', 'VENUME1' , '$2a$10$uRIUp3k3T3u2bD3RH8RzEuD/rAeZ8zBZVThk6bIsc4Wq/F3c56Kzy', '공연장관리자1', 'test@test.com', 'ACTIVE', '2023-06-03', '2023-06-03')
  , (300001, 'BUSINESS', 'PERFORM1', '$2a$10$uRIUp3k3T3u2bD3RH8RzEuD/rAeZ8zBZVThk6bIsc4Wq/F3c56Kzy', '공연관리자1'  , 'test@test.com', 'ACTIVE', '2023-06-03', '2023-06-03')
;
INSERT INTO `business`
    (business_license, `type`, user_id)
VALUES
    ('123-123-1234', 'VENUE_MANAGER', 200001)
  , ('123-123-1234', 'PERFORMANCE_MANAGER', 300001)
;

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


-- PERFORMANCE_SEATS
INSERT INTO `performance_seat`
    (performance_seat_id, `number`, seat_type, status, performance_id, seat_id, user_id)
VALUES
    (100001,  'A1',    'PREMIUM', 'AVAILABLE', 100001, 100001, null)
  , (100002,  'A2',    'PREMIUM', 'AVAILABLE', 100001, 100002, null)
  , (100003,  'A3',    'PREMIUM', 'AVAILABLE', 100001, 100003, null)
  , (100004,  'A4',    'PREMIUM', 'AVAILABLE', 100001, 100004, null)
  , (100005,  'A5',    'PREMIUM', 'AVAILABLE', 100001, 100005, null)
  , (100006,  'A6',    'PREMIUM', 'AVAILABLE', 100001, 100006, null)
  , (100007,  'A7',    'PREMIUM', 'AVAILABLE', 100001, 100007, null)
  , (100008,  'A8',    'PREMIUM', 'AVAILABLE', 100001, 100008, null)
  , (100009,  'A9',    'PREMIUM', 'AVAILABLE', 100001, 100009, null)
  , (100010, 'A10',    'PREMIUM', 'AVAILABLE', 100001, 100010, null)
  , (100101,  'B1',        'VIP',   'PENDING', 100001, 100101, 100002)
  , (100102,  'B2',        'VIP',   'PENDING', 100001, 100102, 100002)
  , (100103,  'B3',        'VIP',   'PENDING', 100001, 100103, 100002)
  , (100104,  'B4',        'VIP', 'AVAILABLE', 100001, 100104, null)
  , (100105,  'B5',        'VIP', 'AVAILABLE', 100001, 100105, null)
  , (100106,  'B6',        'VIP', 'AVAILABLE', 100001, 100106, null)
  , (100107,  'B7',        'VIP', 'AVAILABLE', 100001, 100107, null)
  , (100108,  'B8',        'VIP', 'AVAILABLE', 100001, 100108, null)
  , (100109,  'B9',        'VIP', 'AVAILABLE', 100001, 100109, null)
  , (100110, 'B10',        'VIP', 'AVAILABLE', 100001, 100110, null)
  , (110001,  'C1',    'REGULAR', 'AVAILABLE', 100001, 110001, null)
  , (110002,  'C2',    'REGULAR', 'AVAILABLE', 100001, 110002, null)
  , (110003,  'C3',    'REGULAR', 'AVAILABLE', 100001, 110003, null)
  , (110004,  'C4',    'REGULAR', 'AVAILABLE', 100001, 110004, null)
  , (110005,  'C5',    'REGULAR', 'AVAILABLE', 100001, 110005, null)
  , (110006,  'C6',    'REGULAR', 'AVAILABLE', 100001, 110006, null)
  , (110007,  'C7',    'REGULAR', 'AVAILABLE', 100001, 110007, null)
  , (110008,  'C8',    'REGULAR', 'AVAILABLE', 100001, 110008, null)
  , (110009,  'C9',    'REGULAR', 'AVAILABLE', 100001, 110009, null)
  , (110010, 'C10',    'REGULAR', 'AVAILABLE', 100001, 110010, null)
;

-- PRICE POLICY
INSERT INTO `price_policy`
    (price_policy_id, performance_id, `type`, price)
VALUES
    (100001, 100001, 'PREMIUM'   , 200000)
  , (100002, 100001, 'VIP'       , 160000)
  , (100003, 100001, 'REGULAR'   , 100000)
  , (100004, 100001, 'ACCESSIBLE',  80000)
;