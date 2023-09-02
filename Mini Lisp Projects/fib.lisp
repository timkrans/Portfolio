(defun fib (a b)
    (let ((x a))
        (defvar z 1)
        (defvar g 0)
        (loop while (<= x b) do
            (print x)
            (setq g x)
            (setq x (+ z g))
            (setq z g)
        )
    )
)

(fib 0 100)
 
