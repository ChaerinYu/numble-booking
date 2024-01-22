-- USER
INSERT INTO `users`
    (`dtype`, `user_id`, `created_by`, `created_date`, `created_time`, `last_modified_by`, `last_modified_date`, `last_modified_time`,
    `email`, `last_login_date`, `last_password_modify_date`, `login_id`, `name`, `password`, `status`)
VALUES
    ('USER', '9999', '9999', '2024-01-01', '00:00:00', '9999', '2024-01-01', '00:00:00',
    'chaerin.du.ub@gmail.com', '2024-01-01 00:00:00', '2024-01-01 00:00:00', 'SYSTEM', 'SYSTEM', 'SYSTEM', 'ACTIVE')
;

-- Venue
INSERT INTO `venue`
    (`venue_id`, `created_by`, `created_date`, `created_time`, `last_modified_by`, `last_modified_date`, `last_modified_time`,
    `capacity`, `name`, `possible_times`, `type`)
VALUES
    ('1000', '9999', '2024-01-01', '00:00:00', '9999', '2024-01-01', '00:00:00', '10', 'SMALL VENUE', '10:00~20:00', 'GENERAL_ADMISSION')
;
