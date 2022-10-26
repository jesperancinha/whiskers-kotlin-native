from paragraph_sender import send_paragraphs
import requests

send_paragraphs()

requests.get('http://localhost:8080/story/paragraphs')

requests.get('http://localhost:8080/cat/sayings')
