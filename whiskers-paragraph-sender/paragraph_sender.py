import requests


class paragraphColor:
    PURPLE = '\033[95m'
    BLUE = '\033[94m'
    CYAN = '\033[96m'
    GREEN = '\033[92m'
    YELLOW = '\033[93m'
    RED = '\033[91m'
    RESET = '\033[0m'
    BOLD = '\033[1m'
    UNDERLINE = '\033[4m'


def send_paragraphs():
    with open('../docs/good.story/good.story.chapter.2.md') as f:
        contents = f.read()
        print(contents)
        for paragraph in contents.split("\n\n"):
            print("{0}************************************************".format(paragraphColor.GREEN))
            print("{0}{1}{2}".format(paragraphColor.YELLOW, paragraph, paragraphColor.RESET))
            url = 'http://localhost:8080/story/paragraph'
            x = requests.post(url, json={
                'text': paragraph
            })
            print("{0}Response: {1}{2}".format(paragraphColor.PURPLE, x.text, paragraphColor.RESET))


def send_paragraphs_no_log():
    with open('../docs/good.story/good.story.chapter.2.md') as f:
        contents = f.read()
        for paragraph in contents.split("\n\n"):
            url = 'http://localhost:8080/story/paragraph'
            requests.post(url, json={
                'text': paragraph
            })


def send_paragraphs_list():
    with open('../docs/good.story/good.story.chapter.2.md') as f:
        contents = f.read()
        paragraphs = []
        for paragraph in contents.split("\n\n"):
            paragraphs.append({
                "text": paragraph
            })
        url = 'http://localhost:8080/story/paragraphs/encoded'
        x = requests.post(url, json=paragraphs)
        print(x)
        print(x.text)


def send_paragraphs_list_no_log():
    with open('../docs/good.story/good.story.chapter.2.md') as f:
        contents = f.read()
        paragraphs = []
        for paragraph in contents.split("\n\n"):
            paragraphs.append({
                "text": paragraph
            })
        url = 'http://localhost:8080/story/paragraphs/encoded'
        requests.post(url, json=paragraphs)


def send_paragraph():
    with open('../docs/good.story/good.story.chapter.2.md') as f:
        contents = f.read()
        paragraphs = []
        for paragraph in contents.split("\n\n"):
            paragraphs.append({
                "text": paragraph
            })
        url = 'http://localhost:8080/story/paragraph/encoded'
        print(paragraphs[7])
        x = requests.post(url, json=paragraphs[7])
        print(x)
        print(x.text)


def send_paragraph_no_log():
    with open('../docs/good.story/good.story.chapter.2.md') as f:
        contents = f.read()
        for paragraph in contents.split("\n\n"):
            url = 'http://localhost:8080/story/paragraph/encoded'
            requests.post(url, json={
                "text": paragraph
            })
