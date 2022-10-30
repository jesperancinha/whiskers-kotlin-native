import requests
from paragraph_sender import send_paragraphs_no_log

send_paragraphs_no_log()

for num in range(1, 501):
    requests.get('http://localhost:8080/story/paragraphs/encoded')

    requests.get('http://localhost:8080/cat/sayings/encoded')
