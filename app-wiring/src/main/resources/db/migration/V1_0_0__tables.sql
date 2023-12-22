CREATE TABLE profile (
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE label (
    id SERIAL PRIMARY KEY,
    label TEXT
);

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    name TEXT,
    email TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    label_id INTEGER,
    profile_id INTEGER,

    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),

    FOREIGN KEY (label_id) REFERENCES label(id),
    FOREIGN KEY (profile_id) REFERENCES profile(id)
);

CREATE TABLE skills (
    id SERIAL PRIMARY KEY,
    name TEXT,
    description TEXT,
    label_id INTEGER,

    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),

    FOREIGN KEY (label_id) REFERENCES label(id)
);

CREATE TABLE snapshots(
    id SERIAL PRIMARY KEY,
    user_id INTEGER,
    created_at DATE DEFAULT CURRENT_DATE,

    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE snapshot_answer(
    id SERIAL PRIMARY KEY,
    snapshot_id INTEGER,
    skill_id INTEGER,
    rating INTEGER,

    FOREIGN KEY(snapshot_id) REFERENCES snapshots(id),
    FOREIGN KEY(skill_id) REFERENCES skills(id)
);


CREATE TABLE clients(
    id SERIAL PRIMARY KEY,
    name TEXT,
    description TEXT,
    created_at DATE DEFAULT CURRENT_DATE
);

CREATE TABLE teams(
    id SERIAL PRIMARY KEY,
    name TEXT,
    description TEXT,
    created_at DATE DEFAULT CURRENT_DATE,
    team_leader_id INTEGER,
    client_id INTEGER,

    FOREIGN KEY(client_id) REFERENCES clients(id),
    FOREIGN KEY(team_leader_id) REFERENCES users(id)
);

CREATE TABLE team_member(
    id SERIAL PRIMARY KEY,
    user_id INTEGER,
    team_id INTEGER,

    FOREIGN KEY(user_id) REFERENCES users(id),
    FOREIGN KEY(team_id) REFERENCES teams(id)
)