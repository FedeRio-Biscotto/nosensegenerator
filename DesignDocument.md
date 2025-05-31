# Design Document

## 1. Introduction

### Project purpose
The "Nosense Generator" aims to analyze and generate nonsense sentences in English. It includes functionalities for syntactic analysis via Google NLP API, phrase generation using templates and word dictionaries, and options to expand and manage linguistic structures.

### Scope
The application is written in Java, modular, and uses external APIs and resource files to create fun and nonsensical sentences.

---

## 2. Domain Model

### Main Entities

- **Token**: Base class representing a generic token or word.
- **MyNoun**: Subclass of Token representing a noun.
- **MyVerb**: Subclass of Token representing a verb.
- **MyAdjective**: Subclass of Token representing an adjective.
- **MyDictionary**: Storage of words categorized by type; provides random words.
- **Template**: Blueprint for sentence structure, using various words.
- **TemplatesLibrary**: Collection of multiple templates.
- **Generator**: Orchestrates the process of generating sentences using dictionary and templates.

### Relationships

- **MyNoun**, **MyVerb**, and **MyAdjective** are subclasses of **Token**.
- **MyDictionary** contains multiple **MyNoun**, **MyVerb**, and **MyAdjective** instances.
- **Template** uses multiple **MyNoun**, **MyVerb**, and **MyAdjective** to fill placeholders.
- **TemplatesLibrary** contains multiple **Template** objects.
- **Generator** uses **MyDictionary**, **TemplatesLibrary**, and a **Template** to generate sentences or linguistic structures.

``` plantuml
@startuml

'!include Graphic.puml

class Token {
- token: String
}

class MyNoun
class MyVerb
class MyAdjective

class MyDictionary

class Template {
- template: String
}

class TemplatesLibrary

class Generator

MyNoun -|> Token
MyVerb -|> Token
MyAdjective -|> Token

MyDictionary "1" *-- "*" MyNoun
MyDictionary "1" *-- "*" MyVerb
MyDictionary "1" *-- "*" MyAdjective


Template "1" -- "*" MyNoun
Template "1" -- "*" MyVerb
Template "1" -- "*" MyAdjective

TemplatesLibrary "1" -- "*" Template

Generator "1" -- "1" MyDictionary
Generator "1" -- "1" TemplatesLibrary
Generator "1" -- "1" Template

@enduml
```

---

## 3. Sequence Diagrams

### Sentence analysis
1. User inputs a sentence
2. System sends sentence to Google NLP API
3. Receives syntactic analysis
4. Displays results to the user

``` plantuml
@startuml System_Sequence_Diagrams_SentenceAnalysis
' !include Graphic.puml

actor User
participant "Frontend (Browser)" as FE
participant "GeneratorController" as Controller
participant "ApiGoogleAnalizer" as analize

== Input sentence and analize==
User -> FE: Insert input Sentence and apiKey
FE -> Controller : POST /Tree(inputSentence, apiKey)
Controller -> analize : Semantic_Tree(sentence, apiKey)

==Output==
analize --> Controller : return Semantic Tree
Controller --> FE : JSON with result
FE --> User : Display Semantic Tree

@enduml
```

### Sentence generation
1. User selects a template
2. System fetches random words from dictionaries
3. Constructs and displays the sentence

``` plantuml
@startuml

'!include Graphic.puml

actor User
participant "Frontend (Browser)" as FE
participant "GeneratorController" as Controller
participant "Generator" as Generator
participant "TemplatesLibrary" as TemplatesLibrary
participant "ApiGoogleAnalizer" as analize
participant "MyDictionary" as Dictionary
participant "Template" as Template
participant "ApiGoogleToxicity" as Moderator

== Generation Request ==

User -> FE: Enter ApiKey
User -> FE : Enter sentence (optional) and select parameters
FE -> Controller : POST /generate(inputSentence, tense, mode...)
Controller -> Generator : genSentence(sentence, tense, apiKey)

== Loading template ==

Generator -> TemplatesLibrary : RandomTemplatePicker()
TemplatesLibrary --> Generator : Template

== Preparing tokens and filling template ==
Generator -> analize: LanguageApi(sentence, apiKey)
analize --> Generator: Save tokens in lists

loop for each requested token type
    Generator -> Dictionary : getNoun() / getVerb() / getVerb_nothirdperson() / getAdj()
    Dictionary --> Generator : Token add to lists
end

Generator -> Template : FillTemplate(...) (or FillTemplate_past / future)
Template --> Generator : generated sentence

== Toxicity check ==

Generator -> Moderator : checkToxicity(sentence)
Moderator --> Generator : true / false

alt excessive toxicity
    Generator -> Generator : regenerate sentence
end

Generator --> Controller : generated sentence (or error message)
Controller --> FE : JSON with result
FE --> User : Display sentence

@enduml
```

---

## 4. Main Class Design

```java
// Word class
public class Word {
    String text;
    WordType type; // Enum: NOUN, VERB, ADJECTIVE
}

// Sentence class
public class Sentence {
    List<Word> words;
    String generateSentence();
}

// Template class
public class Template {
    String pattern; // e.g., "[NOUN] [VERB] the [ADJECTIVE] [NOUN]"
    List<Word> fillWords();
}

// Dictionary class
public class Dictionary {
    Map<WordType, List<String>> wordsByType;
    void loadFromFile(String filename);
    String getRandomWord(WordType type);
}
```

---

## 5. UML Diagrams
- **Class Diagram**: shows relationships between `Word`, `Sentence`, `Template`, `Dictionary`.
- **Sequence Diagrams**: depict analysis and generation workflows.

*(These diagrams can be generated with PlantUML or draw.io)*

---

## 6. Technical notes
- The system uses Google Natural Language API for syntactic analysis
- Word lists are loaded from JSON or CSV files
- Sentence generation uses predefined patterns and random words
- Generated sentences can be stored in JSON/XML files
- Design patterns recommended: Factory, Singleton

---

## 7. Final considerations
- Modularity and scalability: easy to extend with new templates and languages
- Easily customizable via configuration and dictionaries
- The architecture supports future API integrations and features

---

## Appendix: Recommended tools
- **PlantUML** or draw.io for UML diagrams
- **Java** IDEs like IntelliJ IDEA or Eclipse
- **Resource files**: JSON, CSV for dictionaries
- **Google NLP API** for linguistic analysis

---
