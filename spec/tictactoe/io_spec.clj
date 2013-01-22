(ns tictactoe.io-spec
  (:require [speclj.core :refer :all]
			[tictactoe.board :refer [make-board]]
            [tictactoe.io :refer :all]))

(describe "IO"
	(context "get-input"
		(it "should receive user input" 
			(should= "o hi dere" (with-in-str "o hi dere" (get-input)))))
		
	(context "print-output"
		(it "should print an output" (with-redefs [println (constantly "output")] 
			(should= "output" (print-output "o hi dere")))))
		
	(context "prompt"
		(let [question "Who did you see at Burger King?" celebrity-name "Beyonce"]
			(it "should prompt a user with a message and then receive user input"
				(with-redefs [println (constantly celebrity-name)]
					(should= celebrity-name (with-in-str celebrity-name (prompt question)))))))
										
	(context "replace-nil"
		(it "should replace a specified value with a space"
			(should= '(" ") (replace-nil [nil] " "))
			(should= '(1 " ") (replace-nil [1 nil] " "))))
					
	(context "printable-board"
		(it "returns a nicely formatted board!"
			(let [board (make-board 9 "x")] (should= "_______\n|x|x|x|\n|x|x|x|\n|x|x|x|\n_______" (printable-board board)))
			(let [board (make-board 9 nil)] (should= "_______\n| | | |\n| | | |\n| | | |\n_______" (printable-board board)))))

	(context "printable-row"
		(it "returns a nicely formatted row!"
			(let [row '(x x x)] (should= "|x|x|x|\n" (printable-row row))))))

(run-specs)