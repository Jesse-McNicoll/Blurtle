
CREATE TABLE users (
    user_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    longest_streak INT NOT NULL DEFAULT 0,
    current_streak INT NOT NULL DEFAULT 0,
    last_solved DATE
);

CREATE TABLE puzzles (
    puzzle_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    puzzle_date DATE NOT NULL UNIQUE,
    puzzle_word TEXT NOT NULL,
    scrambled_word TEXT NOT NULL
);

CREATE TABLE puzzle_results (
    user_id INT NOT NULL,
    puzzle_id INT NOT NULL,
    num_guesses INT NOT NULL,
    puzzle_solved BOOLEAN NOT NULL,

    --Generate primary key from the two foreign keys, which also forces
    -- uniqueness of the combination of the two foreign keys
    PRIMARY KEY (user_id, puzzle_id),

    -- Add constraints identifying the two foreign keys
    CONSTRAINT fk_puzzle_results_user FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT fk_puzzle_results_puzzle FOREIGN KEY (puzzle_id) REFERENCES puzzles(puzzle_id)
);