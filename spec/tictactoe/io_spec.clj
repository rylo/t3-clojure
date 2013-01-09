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
		(it "should prompt a user with a message and then receive user input"
			(should= "Beyonce" (with-in-str "Beyonce" (prompt "Who did you see at burger king?")))))

)

(run-specs)