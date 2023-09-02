;;https://www.tutorialspoint.com/lisp/lisp_input_output.htm
;;;;akes input and puts in list


(defun in()
    (setq list '(nil))
    (setq x 1)
    (loop while(/= x 0)
        do(setf x (read))
        (if (/= x 0)
            (push x (car list))
        )
    )
    (print (reverse (car list)))
)

(in)