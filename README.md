High Level Design

![alt text](https://github.com/karangupta-dev/REST-ollama-sample/blob/master/src/main/resources/static/REST-API-Ollama-System-Design.jpg?raw=true)


1. Download and install Ollama on local.  
   1.1 By running ollama help you get to what all commands are supported by ollama  
   1.2 Run command ollama run llama3.2:3b
   1.3 The above command will download llama3.2 model on local which can be accessible through command line or Ollama GUI.
2. To view all models installed on the system, open CMD
   2.1 Run command- ollama list
3. Register on https://www.weatherapi.com/ and generate a API key.
4. Setup this project on your local and ensure service is able to run successfully.
5. Add API key to project's environment variable
6. Run below curl
  curl --location 'http://localhost:8080/ollama-chat' \
--header 'Content-Type: application/json' \
--data '{
    "query": "what is today'\''s temperature in Utah, US?"
}'
