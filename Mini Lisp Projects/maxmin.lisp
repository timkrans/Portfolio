;;https://www.tutorialspoint.com/lisp/lisp_lists.htm
;;makes a list and used list find max and min
(defun maxmin (mylist)
    (setq h 0)
    (setq l 10000)
    (loop for x in mylist
    do 
        (if (> x h)
            (setq h x)
        )
        (if (< x l)
            (setq l x)
        )
    )
    (format t "~% min value of a is ~d " l)
    (format t "~% max value of a is ~d " h)
)
(defparameter mylist '(1 2 3))
(maxmin mylist)
(dotimes (a 3)

(print a))

(write-line "")