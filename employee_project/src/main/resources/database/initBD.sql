CREATE TABLE IF NOT EXISTS passport (
  id INT NOT NULL AUTO_INCREMENT ,
  passport_id VARCHAR (200) NOT NULL,
  issued_by VARCHAR (200) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS position (
  id INT NOT NULL AUTO_INCREMENT ,
  name VARCHAR (200) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS education (
  id INT NOT NULL AUTO_INCREMENT ,
  name VARCHAR (200) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS employee (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR (200) NOT NULL,
  surname VARCHAR (200) NOT NULL,
  position INT NOT NULL,
  passport INT NOT NULL,
  FOREIGN KEY (position) REFERENCES position (id),
  FOREIGN KEY (passport) REFERENCES passport (id),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS employee_education (
  employee_id INT NOT NULL,
  education_id  INT NOT NULL,
  FOREIGN KEY (employee_id) REFERENCES employee (id) ON DELETE CASCADE,
  FOREIGN KEY (education_id) REFERENCES education (id)  ON DELETE CASCADE
);
