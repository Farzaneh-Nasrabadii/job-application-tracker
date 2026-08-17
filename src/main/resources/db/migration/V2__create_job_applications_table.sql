CREATE TABLE job_applications (
                                  id BIGSERIAL PRIMARY KEY,
                                  company_name VARCHAR(255) NOT NULL,
                                  job_title VARCHAR(255) NOT NULL,
                                  job_url VARCHAR(512),
                                  location VARCHAR(255),
                                  salary VARCHAR(100),
                                  status VARCHAR(50) NOT NULL DEFAULT 'APPLIED',
                                  notes TEXT,
                                  applied_date DATE DEFAULT CURRENT_DATE,
                                  user_id BIGINT NOT NULL,
                                  created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                                  updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                                  CONSTRAINT fk_job_applications_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);