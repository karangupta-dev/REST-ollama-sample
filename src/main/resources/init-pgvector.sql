-- Enable pgvector extension
CREATE EXTENSION IF NOT EXISTS vector;

-- Verify extension is loaded
SELECT 'pgvector extension installed' as status, extname, extversion
FROM pg_extension WHERE extname = 'vector';

-- Test vector functionality
SELECT '[1,2,3]'::vector as test_vector;