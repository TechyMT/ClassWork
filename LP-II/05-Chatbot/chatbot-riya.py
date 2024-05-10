import random


class StoreChatbot:
    def __init__(self):
        self.products = {
            "clothes": {
                "range": "1000 to 5000",
                "brands": ["Levi's", "H&M", "Zara"],
                "sizes": ["S", "M", "L", "XL"],
            },
            "shoes": {
                "range": "2000 to 20000",
                "brands": ["Nike", "Puma", "Adidas"],
                "sizes": ["7", "8", "9", "10"],
            },
            "watches": {
                "range": "500 to 10000",
                "brands": ["Rolex", "Casio", "Fossil"],
                "sizes": ["Small", "Medium", "Large"],
            },
        }
        self.context = None

    def welcome_message(self):
        return "Welcome to our store! How can I assist you today?"

    def respond(self, user_input):
        if "products" in user_input:
            return "We sell clothes, shoes, and watches. What would you like to know about?"
        elif "clothes" in user_input:
            self.context = "clothes"
            return "Sure, what would you like to know about our clothes?"
        elif "shoes" in user_input:
            self.context = "shoes"
            return "Sure, what would you like to know about our shoes?"
        elif "watches" in user_input:
            self.context = "watches"
            return "Sure, what would you like to know about our watches?"
        elif self.context:
            if "price" in user_input or "cost" in user_input:
                return f"The range of prices for {self.context} is {self.products[self.context]['range']}."
            elif "brands" in user_input:
                return f"We sell {', '.join(self.products[self.context]['brands'])} in the {self.context} category."
            elif "sizes" in user_input or "size" in user_input:
                return f"We have {self.context} available in sizes {', '.join(self.products[self.context]['sizes'])}."
            else:
                return "I'm sorry, I didn't understand that. Could you please ask about prices, brands, or sizes?"
        else:
            return "I'm sorry, I didn't understand that. Could you please ask about our products?"


if __name__ == "__main__":
    bot = StoreChatbot()
    print(bot.welcome_message())
    while True:
        user_input = input("User: ").lower()
        if user_input == "exit":
            print("Goodbye!")
            break
        response = bot.respond(user_input)
        print("Chatbot:", response)
