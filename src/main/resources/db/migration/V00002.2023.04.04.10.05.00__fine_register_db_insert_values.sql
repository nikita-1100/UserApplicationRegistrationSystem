INSERT INTO app_user(
    id, password, username)
VALUES (1, 123, 'user'),
       (2, 123, 'admin'),
       (3, 123, 'operator'),
       (4, 123, 'user2'),
       (5, 123, 'operator2');

INSERT INTO user_role(
    user_id, role)
VALUES (1, 'USER'),
       (2, 'ADMIN'),
       (3, 'OPERATOR'),
       (4, 'USER'),
       (5, 'OPERATOR');

INSERT INTO request(id, created, message, status, author_id)
VALUES (1, '2023-04-01 03:39:24.257', 'Fix NullPointerException', 'RECEIVED', 1),
       (2, '2023-04-01 04:39:24.257', 'To clarify with the customer the details of the specification', 'SENT', 1),
       (3, '2023-04-01 05:39:24.257', 'Write automated tests', 'DRAFT', 1),
       (4, '2023-04-01 06:39:24.257', 'Hold CodeReview', 'DRAFT', 1),
       (5, '2023-04-01 07:39:24.257', 'Check the correctness of work on older versions', 'DRAFT', 1),
       (6, '2023-04-01 08:39:24.257', 'Perform refactoring', 'DRAFT', 1),
       (7, '2023-04-01 09:39:24.257', 'Add a feature', 'DRAFT', 4),
       (8, '2023-04-01 10:39:24.257', 'Put constants in Properties', 'SENT', 4),
       (9, '2023-04-01 11:39:24.257', 'Update libraries in the project', 'SENT', 4),
       (10, '2023-04-01 12:39:24.257', 'Work out naming', 'SENT', 4),
       (11, '2023-04-01 13:39:24.257', 'Remove Deprecated method', 'REJECTED', 4),
       (12, '2023-04-01 14:39:24.257', 'Make a backup of the database', 'SENT', 4),
       (13, '2023-04-01 15:39:24.257', 'To work out the possibility of using open source', 'DRAFT', 4);

