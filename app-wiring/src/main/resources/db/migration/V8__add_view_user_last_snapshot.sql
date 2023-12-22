create or replace view view_user_last_snapshot as
select u.id as user_id, u.name as user_name, s.id as skill_id, s.name as skill_name,
sna.rating as rating,
CASE
        WHEN sna.rating BETWEEN 1 AND 2 THEN 'LOW'
        WHEN sna.rating BETWEEN 3 AND 4 THEN 'MED'
        ELSE 'ADV'
END AS level,
snap.id as snapshot_id, snap.created_at as last_snapshot
from users u, skills s,snapshot_answer sna, snapshots snap
where s.id = sna.skill_id
and snap.id  = sna.snapshot_id
and snap.user_id  =  u.id
and snap.created_at  = (select max(created_at) from snapshots where user_id = u.id)