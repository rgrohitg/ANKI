Anki
Anki is a spaced repetition flashcard program. Anki (暗記) is the Japanese word for memorization.

A student is proposed a Deck of cards, one face of the card contains a question, the other face the answer. The student studies the question, tries to guess the answer and then looks if the answer he guessed was correct.

If the student did not know the answer of the Card, he places the card in a red box
If he did know a part of the answer of the Card, he places the card in a orange box
If he did know the answer of the Card, he places the card in a green box
When all the card has been seen, the session is over :

The cards in the red box will be studied again the same day
The cards in the orange box will be studied again the next day
The cards in the green box, will be studied again two days later
For example:

The 1st of may, the student starts studying a deck of cards, it is composed of three cards A, B and C (sort in alphabetic order by question name). The student puts the card A in the red box, the B card in the green box and the card C in the orange box. There is no cards left to study, but one of them, the card A, has been put in the red box and he has to study it again, he partly knows it now and put it in the orange box. The session of 1rst of may is over, the student takes the cards of the orange box and put them in the red box, and the cards from the green box in the orange.

The 2nd of may, the student starts another session, the cards he needs to study are in the red box. He studies the card A, and remember the answer pretty well and put it in the green box. He studies the card C and he stills have hard time to remember it perfeclty and put it again in the orange box.

The session of 2nd of may is over, the student takes the cards of the orange box and put them in the red box, and the cards from the green box in the orange. The session goes on like this until all the cards are found in the green box at the end of a session !

The goal of the exercise is : based on a file containing all the cards of a deck about art, to simulate the study sessions day by day. The user will run the program every day and the session will be interactive. The state at the end of a session can be stored in a file, the program will then say goodby and stop. When the user will run it again the next day it will automaticaly load the file containing the cards to study. When the program will finish with all the cards in the green box, it will congratulate the user and quit.

Example of input file :

card question|card answer
What enzyme breaks down sugars mouth and digestive tract?|Amylase
How is dietary cholesterol transported to target tissues?|In chylomicrons
What is the glucose transporter in the brain and what are its properties?|GLUT-1 transports glucose across blood-brain barrier, GLUT-3 transports glucose into neurons.  Both are high-affinity.
You are invited to share your result sending at dev[AT]weekendesk.fr.

IMPORTANT NOTES:

do not forget Java is an OOP language
we prefer code quality and tests over performance !
we prefer code quality and tests over awesome features !