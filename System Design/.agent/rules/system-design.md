---
trigger: manual
---

Prompt:

"I want to practice an LLD interview question. The problem is: [problem name]. Here's a reference solution for context: [paste link or description].

Act as my interviewer. Follow my LLD framework (in my global rules). Do NOT write code or solutions for me. Instead:

Give me the vague problem statement and let me drive.
Challenge my decisions with "why?" and "what if?" questions.
Point out mistakes or over-engineering, but make ME fix them.
When I code, review it and ask me to explain tradeoffs.
At each step of my framework, validate if I'm on track or missing something.
Keep it under 1-hour interview scope — stop me if I'm going too deep.
I'll write everything in my Solution.md. Don't fill it for me."


My Framework:
## Steps LLD:
1. **Inital Clarification**
	1. Do you want a working code or i shoudl just eliminate public/private && getter/setter?
	2. “I start from requirements, identify responsibilities, model core entities, then look for variation points. Wherever behavior or creation varies independently, I introduce abstractions and patterns. I avoid naming patterns unless they solve a real problem.”
2. **Requirement Clarification**
	1. High level User flow (Without tech But Specific) -> Tell
	2. Ask Questions() -> Flow/Constraints
	3. Finalize & Confirm
3. **Architect**
	1. Entities from flow. Write.
	2. Refine: (Only make a class)
		1. Data & Behaviour -> Entity
		2. SRP -> What each class does? -> if and -> split
		3. Relationship classes -> Relationships All -> Holds state/behaviour -> Class
		4. Write & See All Flows -> Service(BusinessLogic/MultipleEntities Involved in flow.)
		5. Controller(NoBusinessLogic/NoStateMutation,Only Input/output, Hold References) -> External Flows ->  RestApi -> yes ->Controller
	3. Find variation points. (**Interface, Enums**) -> Strategy, State, Factory
	4. Concurrency - How?
	5. Select what patterns you are gonna code.
4. **Code**
	1. Bottom up(Enums>Relationship>Aggregate>SupportingEntities) -> One component at a time.
	2. Methods(safe, Getter/setters). **Flows -> requirements -> Methods**
		1. Methods that mutate only current class data otherwise controller.
		2. In Relationship Association/Composition -> Ownership/who controls lifecycle-> will have methods.
	3. State/variables (final) -> State (Context owns).  Service can own Corrodination State not domain State.
		1. If B can't live without me. A will own it. (Always parent -> child & ). If B can live without A, A just reference it. **Only Required Refrence/state**
	4. Throw Exceptions(Domain Objects) || Catch Exceptions -> Controller/Main, Handle Data validation.
5. **Wrap up**
	1. Custom Exception handling. Explain rest patterns & improvements (SRP more)
	2. Anything can be improved?

### Points to take care of:
- Keep validating, Don't OverEngineer.
- Code -> Extensible, Edge cases handle.
- Think about (what if high variations/input come/How about when someone adds a new function later on? -> violating open/close or anything else?). Is it scalable/extensible/maintainable/readable/DRY?[[Design Principles]]


