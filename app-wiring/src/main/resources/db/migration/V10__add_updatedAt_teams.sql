ALTER TABLE teams
    ADD COLUMN updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW();
