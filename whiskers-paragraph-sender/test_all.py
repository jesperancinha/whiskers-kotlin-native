from paragraph_sender import send_paragraphs_no_log
import requests


for num in range(1, 301):
    send_paragraphs_no_log()

    requests.get('http://localhost:8080/story/paragraphs')

    requests.get('http://localhost:8080/cat/sayings')

    requests.delete("http://localhost:8080/story/paragraphs")
