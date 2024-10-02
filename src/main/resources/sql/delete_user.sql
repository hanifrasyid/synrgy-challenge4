CREATE OR REPLACE PROCEDURE public.delete_user(req_id UUID)
	LANGUAGE plpgsql
AS $procedure$
	BEGIN
		delete from users where id = req_id;
	END;
$procedure$
;
