import json
from difflib import get_close_matches

def load_knowledge_base(file_path: str) -> dict:
    with open(file_path, 'r') as f:
        data: dict = json.load(f)
    return data

def save_knowledge_base(file_path: str, data: dict):
    with open(file_path, 'w') as f:
        json.dump(data, f, indent=2)

def find_best_match(user_question: str, questions: list[str]) ->str |None:
    matches: list = get_close_matches(user_question, questions, n=1, cutoff=0.6)
    return matches[0] if matches else None

def get_answer_for_question(question: str, knowledge_base: dict) -> str | None:
    for q in knowledge_base["question"]:
        if q["question"] == question:
            return q["answers"]

def chat_bot():
    knowledge_base: dict = load_knowledge_base("knowledge_base.json")
    while True:
        user_input: str = input("You: ")
        if user_input.lower() == "exit":
            break
        best_match: str | None = find_best_match(user_input, [q["question"] for q in knowledge_base["question"]])

        if best_match:
            answer: str = get_answer_for_question(best_match, knowledge_base)
            print(f'Bot: {answer}')
        else:
            print('Bot: I don\'t know the answer to that question. Can you provide me with the answer?')
            new_answer: str = input('Type the answer or "skip" to skip')
            if new_answer.lower() != "skip":
                knowledge_base["question"].append({"question": user_input, "answers": [new_answer]})
                save_knowledge_base("knowledge_base.json", knowledge_base)
                print("Bot: Thank you for the new information. I will remember it for the next time.") 

if __name__ == "__main__":
    chat_bot()
