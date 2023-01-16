  CREATE TABLE expenses (
      id BIGINT NOT NULL AUTO_INCREMENT,
      description VARCHAR(100) NOT NULL,
      entry_value DECIMAL(8, 2) NOT NULL,
      create_at DATE NOT NULL,

      PRIMARY KEY(id)
  );