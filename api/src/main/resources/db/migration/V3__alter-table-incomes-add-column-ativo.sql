ALTER TABLE incomes ADD active TINYINT;
UPDATE incomes SET active = 1;
ALTER TABLE incomes MODIFY active TINYINT NOT NULL;