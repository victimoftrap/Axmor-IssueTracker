CREATE TABLE IF NOT EXISTS issues(
  id          IDENTITY  PRIMARY KEY,
  name        VARCHAR   NOT NULL,
  status      VARCHAR   NOT NULL,
  created_at  TIMESTAMP NOT NULL,
  author      VARCHAR   NOT NULL,
  description VARCHAR   NOT NULL
);

CREATE TABLE IF NOT EXISTS comments(
  id         IDENTITY  PRIMARY KEY,
  issue_id   INTEGER   NOT NULL,
  author     VARCHAR   NOT NULL,
  text       VARCHAR   NOT NULL,
  new_status VARCHAR   NOT NULL,
  updated_at TIMESTAMP NOT NULL,

  FOREIGN KEY (issue_id) REFERENCES issues(id) ON DELETE CASCADE
);
