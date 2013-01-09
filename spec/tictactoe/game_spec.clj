(ns tictactoe.game-spec
  (:require [speclj.core :refer :all]
            [tictactoe.game :refer :all]
			[tictactoe.board :refer :all]))

(describe "Game"
	(context "game-over?"
		(let [full-board (make-board 9 "x") empty-board (make-board 9 nil) row-vector (generate-rows)]
			(it "should return false if the game isn't over" (should= false (game-over? taken-row-present? empty-board row-vector)))
			(it "should return false if the game isn't over" (should= true (game-over? taken-row-present? full-board row-vector)))))

	; (context "start"
	; 	(let [board (make-board 9 nil)]
	; 		(it "should begin a game loop" (should= true (start (make-board 9 nil))))))
	;
	; how do i (or why would i) test if a loop started?
	;
)

(run-specs)