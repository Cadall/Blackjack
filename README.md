# About Blackjack
Put together this game of blackjack for my portfolio, this mainly serves as a showcase of my game oriented programming and reflection skills. I have some experience using Java Swing and JOptionPane, but I decided to stick with main for my portfolio. When I completed this I noticed there were some issues and really inefficient parts about it, instead of rewriting the program better, I decided to leave it as-is and reflect on the possible improvements here. It functions with just the main class which manages logic, and a Deck class to handle cards.
# Issues and Improvements
### Tracking player/dealer hands
- The most prominent change I could have made is to how I handle the cards. Right now it's all taken care of through the StartGame class, which had me thinking of pretty annoying ways to work around the limitations. It could have been much easier for myself, the functionality, and readability of the code, if I just made a Player class to keep track of which Cards the player/dealer had, their total card value, and taking care of aces dynamic value.
### Player Count Limitation
- Like most good card games, blackjack is usually played with more than 2 people. As it's set up right now, this program only supports one player against a computer controlled dealer. With a good bit of time and effort I could have implemented a system which created a number of player controlled "Player" objects at the start of the round, and gave them each a turn one by one until the end of the round. Doing this would also require the ability to play with more than one deck of cards, and/or to reshuffle used up cards. Seeing as this game doesn't have online functionality, it wouldn't be too useful for very large-scale games, but to sit down with a friend or two and mess around could be quite fun.
