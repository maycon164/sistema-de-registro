ALTER TABLE snapshot_answer
ADD COLUMN willing_to_answer_questions BOOLEAN DEFAULT false,
ADD COLUMN willing_to_present BOOLEAN DEFAULT false,
ADD COLUMN worked_with_tech BOOLEAN DEFAULT false;