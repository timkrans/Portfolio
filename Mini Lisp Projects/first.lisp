(print "hello")

(print (+ 43(* 5 3 4) 7))

;; build a function-lambda-expression

(defun factorial(num)
    (cond ((zerop num) 1)
        (t (* num(factorial (- num 1))))
    )
)

(setq n 5)
(format t "~% The  factorial ~d is ~d" n (factorial n))