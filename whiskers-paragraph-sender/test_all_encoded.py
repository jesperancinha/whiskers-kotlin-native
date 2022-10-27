from paragraph_sender import send_paragraphs_no_log
import requests


for num in range(1, 301):
    requests.get('http://localhost:8080/story/paragraphs/encoded')

    requests.get('http://localhost:8080/cat/sayings/encoded')
