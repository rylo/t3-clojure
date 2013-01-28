(ns tictactoe.game-rules-spec
  (:require [speclj.core :refer :all]
			[tictactoe.board :refer :all]
            [tictactoe.game_rules :refer :all]))

(describe "game_rules"
	(context "game-over?"
			(it "should return false if the game isn't over" 
				(should= false (game-over? (make-board 9 nil))))
			(it "should return false if the game isn't over" 
				(should= true (game-over? ["x" "o" "x" "o" "x" "o" "o" "x" "o"])))
			(it "should return true if the game isn't over" 
				(should= true (game-over? (make-board 9 "x")))))

	(context "valid-move?"
			(it "should return false on invalid moves" 
				(should= false (valid-move? (make-board 9 nil) "asdf")))
			(it "should return false on invalid moves" 
				(should= false (valid-move? (make-board 9 nil) "12222")))
			(it "should return true on valid moves" 
				(should= true (valid-move? (make-board 9 nil) "1"))))
			
	(context "game-over-with-tie?"
		(let [tied-board ["x" "o" "x" "o" "x" "o" "o" "x" "o"]
			  not-tied-board ["x" "x" "x" "o" "x" "o" "o" "x" "o"]]
			(it "should return true if the board is full and nobody has won"
				(should= true (game-over-with-tie? tied-board)))
			(it "should return true if the board is full and somebody has won"
				(should= true (winning-row-present? ["x" "x" "x" "o" "x" "o" "o" "x" "o"]))
				(should= true (full? ["x" "x" "x" "o" "x" "o" "o" "x" "o"]))
				(should= false (game-over-with-tie? ["x" "x" "x" "o" "x" "o" "o" "x" "o"])))))
				
	(context "game-not-over?"
		(it "should return the marker of the winner of the match"
			(let [board (make-board 9 "x")]
				(should= false (game-not-over? board)))))
	
	(context "empty-spaces"
		(it "should return a collection of the indexes of all empty spaces"
			(let [board ["x" "x" "x" "x" "x" "x" "x" "x" nil]] 
				(should= [8] (empty-spaces board)))
			(let [board ["x" "x" "x" "x" "x" "x" nil nil nil]] 
				(should= [6 7 8] (empty-spaces board)))))
			
	(context "get-winner"
		(it "should return the marker of the winner of the match"
			(should= "x" (get-winner (make-board 9 "x")))
			(should= "o" (get-winner (make-board 9 "o")))
			(should= "x" (get-winner ["x" "x" "x" nil nil "o" nil nil nil]))
			(should= nil (get-winner (make-board 9 nil)))
			(should= nil (get-winner [nil "x" nil nil nil "o" nil nil nil]))))
	
	(context "winning-row-present?"
		(it "returns true if a row is taken"
			(let [empty-board (make-board 9 nil) full-board (make-board 9 "x") full-row-vector (generate-winning-combinations)]
				(should= false (winning-row-present? empty-board))
				(should= true (winning-row-present? full-board))
				(should= true (winning-row-present? ["x" "x" "x" "o" "o" nil nil nil nil]))
				(should= true (winning-row-present? [nil "x" "x" "o" "o" "o" nil nil nil]))
				(should= false (winning-row-present? ["x" "o" "o" "o" "o" nil nil nil nil]))))))
	
	(context "winning-move-available?"
		(it "returns true if the row can be won by the player with the supplied marker" 
			(should= true (winning-move-available-in-row? "x" ["x" "x" nil "o" "o" nil nil nil nil] [0 1 2])))
		(it "returns false if the row cannot be won by the player with the supplied marker" 
			(should= false (winning-move-available-in-row? "x" ["x" "x" "x" "o" "o" nil nil nil nil] [0 1 2])))
		(it "returns false if the row cannot be won by the player with the supplied marker" 
			(should= false (winning-move-available-in-row? "x" ["x" "o" nil nil nil nil nil nil nil] [0 1 2])))
		(it "returns false if no rows cannot be won by the player with the supplied marker" 
			(should= false (winning-move-available? "x" ["x" nil nil nil "o" nil nil nil nil] (generate-winning-combinations)))
			(should= true (winning-move-available? "x" ["x" "x" nil nil "o" nil nil nil nil] (generate-winning-combinations)))))

(run-specs)