(ns tictactoe.io-spec
  (:require [speclj.core :refer :all]
            [tictactoe.io :refer :all]))

(describe "IO"
	(context "get-input"
		(it "should receive user input" 
			(should= "o hi dere" (with-in-str "o hi dere" (get-input)))))
		
	(context "set-output"
		(it "should print an output" (with-redefs [println (constantly "output")] 
			(should= "output" (set-output "o hi dere")))))
		
	(context "prompt"
		(let [question "Who did you see at Burger King?" celebrity-name "Beyonce"]
			(it "should prompt a user with a message and then receive user input"
				(with-redefs [println (constantly celebrity-name)]
					(should= celebrity-name (with-in-str celebrity-name (prompt question)))))))

)

(run-specs)