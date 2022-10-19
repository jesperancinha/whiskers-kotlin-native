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


with open('../docs/good.story/good.story.chapter.2.md') as f:
    contents = f.read()
    print(contents)
    for paragraph in contents.split("\n\n"):
        print("{0}************************************************".format(paragraphColor.GREEN))
        print("{0}{1}{2}".format(paragraphColor.YELLOW, paragraph, paragraphColor.RESET))
        url = 'http://localhost:8082/story/paragrah'
        x = requests.post(url, json={
            'text': paragraph
        })
        print("{0}Response: {1}".format(paragraphColor.PURPLE, x.text))
