(ns tictactoe.game-rules-spec
  (:require [speclj.core :refer :all]
			[tictactoe.board :refer :all]
            [tictactoe.game_rules :refer :all]))

(describe "game_rules"
	(context "game-over?"
		(let [full-board (make-board 9 "x") empty-board (make-board 9 nil) row-vector (generate-rows)]
			(it "should return false if the game isn't over" (should= false (game-over? empty-board)))
			(it "should return false if the game isn't over" (should= true (game-over? full-board)))))

	(context "valid-move?"
		(let [board (make-board 9 nil)]
			(it "should return false on invalid moves" (should= false (valid-move? board "asdf")))
			(it "should return false on invalid moves" (should= false (valid-move? board "12222")))
			(it "should return true on valid moves" (should= true (valid-move? board "1")))))
			
	(context "game-over-with-tie?"
		(let [tied-board ["x" "o" "x" "o" "x" "o" "o" "x" "o"]
			  not-tied-board ["x" "x" "x" "o" "x" "o" "o" "x" "o"]]
			(it "should return true if the board is full and nobody has won"
				(should= true (game-over-with-tie? tied-board)))
			(it "should return true if the board is full and somebody has won"
				(should= true (winning-row-present? not-tied-board))
				(should= true (full? not-tied-board))
				(should= false (game-over-with-tie? not-tied-board)))))
	
	(context "emtpy-spaces"
		(it "should return a collection of the indexes of all empty spaces"
			(let [board ["x" "x" "x" "x" "x" "x" "x" "x" nil]] (should= [8] (empty-spaces board)))
			(let [board ["x" "x" "x" "x" "x" "x" nil nil nil]] (should= [6 7 8] (empty-spaces board)))))
			
	(context "get-winner"
		(it "should return the marker of the winner of the match"
			(let [board (make-board 9 "x")]
				(should= "x" (get-winner board))
			)
		)
	)
)

(run-specs)