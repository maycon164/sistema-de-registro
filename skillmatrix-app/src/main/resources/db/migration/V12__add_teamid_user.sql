ALTER TABLE users
ADD COLUMN team_id INTEGER,
ADD FOREIGN KEY (team_id) REFERENCES teams(id);