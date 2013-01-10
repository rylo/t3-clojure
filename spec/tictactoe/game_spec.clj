(ns tictactoe.game-spec
  (:require [speclj.core :refer :all]
            [tictactoe.game :refer :all]
			[tictactoe.board :refer :all]))

(describe "Game"
	(context "game-over?"
		(let [full-board (make-board 9 "x") empty-board (make-board 9 nil) row-vector (generate-rows)]
			(it "should return false if the game isn't over" (should= false (game-over? taken-row-present? empty-board row-vector)))
			(it "should return false if the game isn't over" (should= true (game-over? taken-row-present? full-board row-vector)))))

	(context "valid-move?"
		(let [board (make-board 9 nil)]
			(it "should return false on invalid moves" (should= false (valid-move? board "asdf")))
			(it "should return false on invalid moves" (should= false (valid-move? board "12222")))
			(it "should return true on valid moves" (should= true (valid-move? board "1")))))
	
	(context "get-human-move"
		(it "should return the user's move if it is valid"
			(let [board (make-board 9 nil)]
				(should= "1" (with-redefs [read-line (constantly "1") println (constantly "")] (get-human-move board)))))
		(it "should prompt the user for another move if it is invalid" (pending "ability to test recursive input")))
	
	
	
	; (context "start"
	; 	(let [board (make-board 9 nil)]
	; 		(it "should begin a game loop" (should= true (start (make-board 9 nil))))))
	;
	; how do i (or why would i) test if a loop started?
	;
)

(run-specs)