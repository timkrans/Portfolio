/*https://www.youtube.com/watch?v=SykxWpFwMGs*/
/*facts for classes*/
class(math).
class(science).
class(econ).
class(gym).
class(history).

/*facts for students*/
student(tim).
student(mike).
student(charles).
student(stef).
student(anna).
student(nate).
student(rob).
student(jeff).
student(jane).
student(nicole).

/*facts for enrollment*/
enrolled(tim,math).
enrolled(tim,gym).
enrolled(tim,history).
enrolled(mike,science).
enrolled(mike,gym).
enrolled(mike,history).
enrolled(charles,econ).
enrolled(charles,gym).
enrolled(charles,math).
enrolled(stef,econ).
enrolled(stef,gym).
enrolled(stef,history).
enrolled(anna,econ).
enrolled(anna,gym).
enrolled(anna,science).
enrolled(nate,math).
enrolled(nate,gym).
enrolled(nate,history).
enrolled(rob,science).
enrolled(rob,gym).
enrolled(rob,history).
enrolled(jeff,econ).
enrolled(jeff,gym).
enrolled(jeff,math).
enrolled(jane,econ).
enrolled(jane,gym).
enrolled(jane,history).
enrolled(nicole,econ).
enrolled(nicole,gym).
enrolled(nicole,science).

/*rules that compare two students*/
sameclass(X,Y) :-
    enrolled(X,Z),
    enrolled(Y,Z),
    enrolled(X,G),
    enrolled(Y,G),
    X \= Y,
    Z \= G,
	format('~w and ~w have 2 classes together ~n', [X,Y]);
    format('~w and ~w do not have 2 classes together ~n', [X,Y]).

/* Test cases
    sameclass(tim,anna) output: tim and anna do not have 2 classes together
    sameclass(nate,tim) output: nate and tim have 2 classes together
*/
