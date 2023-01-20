ALTER TABLE expenses ADD active TINYINT;
UPDATE expenses SET active = 1;
ALTER TABLE expenses MODIFY active TINYINT NOT NULL;