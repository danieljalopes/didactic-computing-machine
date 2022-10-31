CREATE TABLE  myschema.messages (
	"i_message" SERIAL PRIMARY KEY,
    "e_user" integer references myschema.users(i_user),
    "text" VARCHAR
    
);