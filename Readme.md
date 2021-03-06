[![DOI](https://zenodo.org/badge/155204989.svg)](https://zenodo.org/badge/latestdoi/155204989)

# Chatbot
This is an intelligent agent that works as a chatbot: a soft-bot (software-based robot) that can chat with the user.
It is a search based agent, and is capable of understanding and answering only the questions that are stored on its knowledge. Since that knowledge is written on simple plain text files, it can be easily changed.
The bot is also able to display the interface, basic extra answers, and store the chat record in English or Spanish. The default is Spanish, but it can be changed from the main interface during runtime.

This bot was developed to be featured in the 2016 College Fair of UTN FRSF (Universidad Tecnológica Nacional, Facultad Regional Santa Fe), in the booth of the Information Systems Engineering degree. The goal was to show interested applicants, mostly high school students, what could be done with programming and artificial intelligence.


## Getting Started
The project was developed on IntelliJ IDEA, with Java8 and JavaFX, and extends FAIA. You can get the latest working source code from the master branch.

FAIA is an framework for developing intelligent agents that was created at CIDISI (Research and Development Centre for Information Systems Engineering), at UTN FRSF by professors and researchers of the Information Systems Engineering.

Once installed, the working knowledge of the agent should be located on C:\Chatbot\resources, and split on several files. Each of them has a unique format.
* **user_questions.txt:** Contains the list of questions that can be asked by the user, in natural language. This should be written properly, with correct punctuation and accents, if corresponds. The format is one question per line, with no empty lines. No empty line at the bottom.
* **unimportant_words.txt:** Contains the list of words that the agent ignores (i.e. the, a, an, as). The format is one word per line, with no empty lines. No empty line at the bottom. These are stop-words.
* **synonyms.txt:** Contains sets of words that can be replaced by a keyword. Synonyms can be either a word, or a statement. The format is a group of synonyms per line, properly written in natural language, with the following shape: keyword|word1,word2,a synonym statement,word3.There should be no empty lines. An example:
```
UTN|Facultad,FRSF,UTN FRSF,Universidad Tecnológica Nacional,Tecnológica,UTN-FRSF
```
* **questions_and_answers.txt:** Main file with the correspondence between questions and answers. Several questions can have the same answers or set of answers (synonym questions), and a question may have more than one answer. The format for this file is given in sets: a group of questions with answers. Each set is written as: a line with the amount of questions in the set, the next lines should be the questions, a new line with the amount of answers, and following lines with the answers. No empty lines should be left between sets, and the file must end with a line that only contains a 0. An example:
```
2
¡Hola!
Hola, ¡buen día!
2
Hola!
Buenos días.
0
```

## Installing
The agent is only available for Windows, and the PC must have the [Java JDK 8u102](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) installed before the agent can be executed.

To install the chatbot, follow the next steps:

1. [Download the latest release](https://github.com/melvidoni/chatbot/releases/tag/2.0.1).
1. Uncompress the *.rar file with "Extract here".
1. Go to your system's drive (`C:/`)
1. Copy the folder Chatbot. It should be located on `C:\Chatbot`.
1. Run the application from `Chatbot.exe`

You can create a desktop shortcut for the file Chatbot.exe by right clicking it, then select "Send to", then "Desktop (create shortcut)".

![Chatbot Interface](http://i64.tinypic.com/1qn4av.png)



## Other Information
More information regarding the project.

#### Built With
* Java 8 and JavaFX
* Extends [FAIA (Framework for Artificial Intelligent Agents)](https://www.doi.org/10.4018/978-1-61350-456-7.ch209)
* IDE: IntelliJ IDEA

#### Versioning
We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [changelog](CHANGELOG.md). 

#### License
This project is licensed under the [Academic Free Licence (AFL) 3.0](https://opensource.org/licenses/AFL-3.0). See the [Licence.md](License) file for details.
