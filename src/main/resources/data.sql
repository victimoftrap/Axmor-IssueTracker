INSERT INTO issues (name, status, created_at, author, description) VALUES
    ('Internal error in annual PDF report', 'RESOLVED', '2010-01-09 13:42:00', 'John Smith',
        'Annual financial report always return internal error to user. It’s work on previous release.'),
    ('Invalid layout for index page', 'CREATED', '2010-04-05 13:42:00', 'Jane Doe', 'Sample text');

INSERT INTO comments (issue_id, author, text, new_status, updated_at) VALUES
    (1, 'Bill Jonhson', 'Error was in security component. Fixed in revision 123.', 'RESOLVED', '2010-02-09 21:20:00');
