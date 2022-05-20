CREATE TABLE IF NOT EXISTS category
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255) UNIQUE NOT NULL ,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS ingredient
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    amount      DECIMAL(19, 2),
    description VARCHAR(255),
    recipe_id   BIGINT,
    unit_id     BIGINT,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS note
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    description LONGTEXT,
    recipe_id   BIGINT,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS recipe
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    cook_time   INTEGER,
    description VARCHAR(255),
    difficulty  VARCHAR(255),
    directions  VARCHAR(255),
    image       LONGBLOB,
    prep_time   INTEGER,
    rating      INTEGER,
    servings    INTEGER,
    source      VARCHAR(255),
    url         VARCHAR(255),
    note_id     BIGINT,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS recipe_category
(
    recipe_id   BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY (recipe_id, category_id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS unit_of_measure
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255) UNIQUE NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

ALTER TABLE recipe ADD CONSTRAINT fk_recipe_key FOREIGN KEY (note_id) REFERENCES note (id);
ALTER TABLE note ADD CONSTRAINT fk_note_recipe FOREIGN KEY (recipe_id) REFERENCES recipe (id);

ALTER TABLE ingredient ADD CONSTRAINT fk_recipe_ingredient FOREIGN KEY (recipe_id) REFERENCES recipe (id);
ALTER TABLE ingredient ADD CONSTRAINT fk_ingredient_uom FOREIGN KEY (unit_id) REFERENCES unit_of_measure (id);

ALTER TABLE recipe_category ADD CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipe (id);
ALTER TABLE recipe_category ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES category (id);