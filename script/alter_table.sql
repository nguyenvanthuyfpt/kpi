
CREATE SEQUENCE kpi_job_scheduler_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE kpi_job_scheduler_seq OWNER TO postgres;

create table kpi_job_scheduler
(
	id bigint NOT NULL DEFAULT nextval('kpi_job_scheduler_seq'::regclass),
	create_date timestamp without time zone,
	job_code VARCHAR(50),
	job_name VARCHAR(50),
	job_exec VARCHAR(50),
	job_cron VARCHAR(50),
	job_status INT DEFAULT 0
)

CREATE SEQUENCE kpi_job_log_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE kpi_job_log_seq OWNER TO postgres;

CREATE TABLE kpi_job_log
(
	id bigint NOT NULL DEFAULT nextval('kpi_job_log_seq'::regclass) ,
	start_exec timestamp without time zone,
	end_exec timestamp without time zone,
	job_id INT, 
	msg_exec VARCHAR(100)
)