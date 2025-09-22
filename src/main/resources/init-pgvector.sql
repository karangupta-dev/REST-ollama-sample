-- Enable pgvector extension
CREATE EXTENSION IF NOT EXISTS vector;

-- Verify installation
DO $$
BEGIN
    IF EXISTS (SELECT 1 FROM pg_extension WHERE extname = 'vector') THEN
        RAISE NOTICE 'pgvector extension is successfully installed';
    ELSE
        RAISE EXCEPTION 'Failed to install pgvector extension';
    END IF;
END $$;

-- Test vector operations
SELECT '[1,2,3]'::vector AS test_vector;
SELECT '[-1,0,1]'::vector <-> '[1,0,-1]'::vector AS cosine_distance;