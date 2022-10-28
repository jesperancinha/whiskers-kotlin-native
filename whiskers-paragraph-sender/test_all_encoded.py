import requests

for num in range(1, 100):
    requests.get('http://localhost:8080/story/paragraphs/encoded')

    requests.get('http://localhost:8080/cat/sayings/encoded')
